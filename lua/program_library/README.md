# Program Library - Tyler Hippard
*For ComputerCraft/CC: Tweaked*

Program Library allows a computer to host a file-sharing server using ComputerCraft's rednet API. 

Files are saved in a configurable directory. The protocol and hostname are configurable as well.

An example client program can be found [here]("./../client)

Features:
- List files for download
- Request file from library
- Send file to library
- Broadcasts a chosen client program on sub-protocol `<protocol>:client` that can be requested and stored on a new computer using the interactive lua prompt in case the computer does not have a client program.
- Get info about library server name, hostname, protocol
- Get info about capabilities of the protocol (a help function)

Files are serialized using `textutils.serialize`, and then transmitted over rednet as a string message.

Messages containing "ack" are used to acknowledge successful transfer of files, or that the client/library is ready for the next step of the transmission.

The library will also respond with true/false boolean messages for statuses, such as "File not found" and "File with that name already exists on the server."
