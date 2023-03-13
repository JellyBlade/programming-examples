local protocol = "proglibv1"
peripheral.find("modem", rednet.open)
local id = rednet.lookup(protocol)
local args = {...}

function printMsg(msg)
    if (type(msg) == "table") then
        for k, v in pairs(msg) do
            if (type(v) == "table") then
                print(k)
                printMsg(v)
            else
                print(k, v)
            end
        end
    else
        print(msg)
    end
end

if (id == nil) then
    print("No host found for " .. protocol)
    return
end

if (#args == 0) then
    rednet.send(id, "help", protocol)
    local _, message = rednet.receive(protocol)
    printMsg(message)
    return
end

if (#args == 2 and args[1] == "request") then
    rednet.send(id, args[1] .. " " .. args[2], protocol)    
    local _, message = rednet.receive(protocol)
    if (message) then
        rednet.send(id, "ack", protocol)
        local _, contents = rednet.receive(protocol)
        local file = fs.open(args[2], "w")
        file.write(textutils.unserialise(contents))
        file.close()
        print(args[2] .. " downloaded successfully.")
        return
    else
        print("File not found in library.")
        return
    end
end

if (#args == 2 and args[1] == "send") then
    if (not fs.exists(args[2])) then
        print("File to send: " .. args[2] .. " not found.")
        return
    end
    local file = fs.open(args[2], "r")
    file = file.readAll()
    local contents = textutils.serialise(file)
    rednet.send(id, args[1] .. " " .. args[2], protocol)
    local _, message = rednet.receive(protocol)
    if (message) then
        rednet.send(id, contents, protocol)
        local status, message = rednet.receive(protocol, 30)
        if (status == nil) then
            print("Something went wrong with the library.")
            return
        end
        if (message == "ack") then
            print("Successfully sent " .. args[2] .. " to library.")
            return
        end    
    else
        print("File with that name already exists in library.")
    end
end

rednet.send(id, args[1], protocol)
local _, message = rednet.receive(protocol)
printMsg(message)
