local label = os.getComputerLabel()
local protocol = "proglibv1"
local hostname = "changeme"
local configPath = "proglib.cfg"
local programFolder = "lib_programs"
local clientProgram = "client.lua"

-- Peripheral check
if (peripheral.find("modem") == nil) then
    printError("Please attach a modem to this computer.")
    return
end

-- Library setup and configuration
local function setup()
    if (fs.exists(configPath)) then
        local configData = {}
        
        for line in io.lines(configPath) do
            table.insert(configData, line)
        end
        
        if (not (#configData == 5)) then
            printError("Malformed proglib.cfg! Re-running setup.")
        else
            programFolder = configData[2]
            protocol = configData[3]
            hostname = configData[4]
            clientProgram = configData[5]
            return
        end
    end
    print("--- Library Setup ---")
    local config = fs.open(configPath, "w")
    
    -- Library label
    if (label == nil) then
        print("Library Computer Label: ")
        label = read()
        os.setComputerLabel(label)
    end
    config.writeLine(label)
    
    -- Library folder
    print("Library Folder Path: ")
    print("Leave blank to use default (" .. programFolder .."): ")
    local newFolder = read()
    if (not (newFolder == nil) and (#newFolder > 0)) then
        programFolder = newFolder
    end
    if (not (fs.exists(programFolder))) then
        fs.makeDir(programFolder)
    end
    config.writeLine(programFolder)
    
    -- Library protocol
    print("Library Protocol:")
    print("Leave blank to use default (" .. protocol .. ")")
    local newProtocol = read()
    if (not (newProtocol == nil) and (#newProtocol > 0)) then
        protocol = newProtocol
    end
    config.writeLine(protocol)
    
    -- Library hostname
    while true do
        local errorFlag = false
        print("Library hostname: ")
        local newHostname = read()
        if (newHostname == nil) then
            printError("Please specify a hostname.")
            errorFlag = true
        end
        -- Lookup hostname
        if (errorFlag == false) then 
            local checkHostname = rednet.lookup(protocol, newHostname)
            if (not checkHostname == nil) then
                printError("Hostname already in use on " .. protocol .. ".")
                errorFlag = true
            end
        end
        if (errorFlag == false) then
            hostname = newHostname
            config.writeLine(hostname)
            break
        end
    end
    
    -- Library client software
    print("Library can broadcast client software for connecting to library.")
    print("Broadcast client software? (y/n): ")
    local response = read()
    if (response == "y" or response == "Y") then
        print("Client software:")
        print("Leave blank to use default (" .. clientProgram .. "): ")
        local newClient = read()
        if (fs.exists(newClient)) then
            clientProgram = newClient
            print("Client program found.")
        else
            clientProgram = nil
            printError("Client program not found, disabling client software broadcast...")
        end
    else
        clientProgram = nil
    end
    config.writeLine(clientProgram)
    print("--- Library Setup Complete ---")
    config.close()
end

-- Client check
if (not (clientProgram == nil)) then
    if (fs.exists(clientProgram) or fs.exists(fs.combine(programFolder, clientProgram))) then
        print("Client program detected: " .. clientProgram)
    else
        clientProgram = nil 
    end
end

-- Register this computer for the protocol
function register()
    peripheral.find("modem", rednet.open)
    rednet.host(protocol, hostname)
    print("Now hosting " .. protocol .. " as " .. hostname)    
    if (not (clientProgram == nil) and (#clientProgram > 0)) then
        rednet.host(protocol .. ":client", hostname)
        print("Broadcasting client software on " .. protocol .. ":client.")
    end
end

-- Unregister this computer from the protocol
function unregister()
    rednet.unhost(protocol)
    print("No longer hosting " .. protocol)
    if (not (clientProgram == nil) and (#clientProgram > 0)) then
        rednet.unhost(protocol .. ":client")
        print("No longer broadcasting client software.")
    end
    peripheral.find("modem", rednet.close)
end

-- Send library info to requester
function info(id)
    local response = "Library: " .. label .. " hosting " .. protocol .. " at " .. hostname .. ".\n" .. protocol .. " is a file-transfer protocol to allow remote computers to request programs and files from the library computer."
    rednet.send(id, response, protocol)
end

-- Send protocol capabilities to the requester
function help(id)
    local response = {}
    response["[info]"]               = "Displays computer name and " .. protocol .. " info."
    response["[request <filename>]"] = "Requests file from this library.\nFirst response is whether file exists.\nAfter you send an ack, the contents of the file will be sent."
    response["[send <filename>]"]    = "Sends the specified file to the library. Server will respond true/false if a file with that name already exists. Send contents of file if true.\nServer will ack file received."
    response["[ack]"]                = "Acknowledge the previous message."
    response["[help]"]               = "Request protocol capabilities from the library."
    response["[list]"]               = "Request file list in a table."
    response["[listStr]"]            = "Request file list in a string."
    rednet.send(id, response, protocol)
end

-- Send requested file
function send(id, requested)
    local filePath = fs.combine(programFolder, requested)
    print(("Computer %s requested file %s."):format(id, requested))
    if (not fs.exists(filePath)) then
        rednet.send(id, false, protocol)
        return
    end
    local file = fs.open(filePath, "r")
    file = file.readAll()
    local contents = textutils.serialise(file)
    rednet.send(id, true, protocol)
    print("File found. Waiting for acknowledgment...")
    local timeout = rednet.receive(protocol, 30) -- ack
    if (timeout == nil) then
        printError("Request from " .. id .. " timed out.")
        return
    end
    print("Sending contents of file: " .. requested)
    rednet.send(id, contents, protocol)
end

-- Receive sent file and save to programFolder
function recv(id, sent)
    local filePath = fs.combine(programFolder, sent)
    print(("Computer %s saving file %s"):format(id, sent))
    if (fs.exists(filePath)) then
        rednet.send(id, false, protocol)
        printError("File " .. sent .. " already exists.")
        return
    else
        rednet.send(id, true, protocol)
    end
    local status, contents = rednet.receive(protocol, 30)
    if (status == nil) then
        printError("Send from " .. id .. " timed out.")
        return
    end
    local file = fs.open(filePath, "w")
    file.write(textutils.unserialise(contents))
    file.close()
    print("Successfully saved file " .. sent .. " from Computer " .. id .. ". ")
    rednet.send(id, "ack", protocol)   
end

-- Send list of files avaible to request
function list(id)
    local files = fs.list(programFolder)
    rednet.send(id, files, protocol)
end

-- Same as list, but as a string instead of a table.
function listStr(id)
    local files = fs.list(programFolder)
    local fileStr = ""
    for i = 1, #files do
        fileStr = fileStr .. files[i] .. "\n"
    end
    rednet.send(id, fileStr, protocol)
end

-- Send error message to client
function error(id, reason)
    rednet.send(id, "Error: " .. reason, protocol)
end

-- Broadcast client software
function clientBroadcast()
    local contents = nil
    local file = nil
    if (fs.exists(clientProgram)) then
        file = fs.open(clientProgram, "r")
    elseif (fs.exists(fs.combine(programFolder, clientProgram))) then
        file = fs.open(fs.combine(programFolder, clientProgram), "r")
    else
        printError("Client software not found, disabling broadcast.")
        return
    end
    file = file.readAll()
    contents = textutils.serialise(file)
    while true do
        rednet.broadcast(contents, protocol .. ":client")
        sleep(5)
    end
end

function main()
    while true do
        local id, message = rednet.receive(protocol)
        if (string.find(message, "info")) then
            info(id)
        elseif (string.find(message, "request")) then
            local name = string.sub(message, 9)
            send(id, name)
        elseif (string.find(message, "send")) then
            local name = string.sub(message, 6)
            recv(id, name)
        elseif (string.find(message, "help")) then
            help(id)
        elseif (string.find(message, "listStr")) then
            listStr(id)
        elseif (string.find(message, "list")) then
            list(id)
        else
            error(id, "Unknown request. Send \"help\" for commands + syntax")
        end    
    end
end

setup()
-- print(label .. hostname .. protocol .. programFolder)
register()
if (not (clientProgram == nil) and (#clientProgram > 0)) then
    parallel.waitForAny(main, clientBroadcast)
else
    main()
end
