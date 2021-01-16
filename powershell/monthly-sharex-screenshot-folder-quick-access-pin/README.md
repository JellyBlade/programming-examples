## Monthly ShareX Screenshot Folder Quick Access Pin - Tyler Hippard
*AKA: The script with the worst name I've ever heard*

This quick script I made entirely for my own purposes simply pins a folder with the name of the current month (intended for screenshot folders) to your Quick Access, and unpius the previous month's folder. Technically it can be used for any folder, and will create a folder with a YYYY-MM format and pin it, so you could use it for anything, really.

Requires: The ability to run powershell scripts using task scheduler

Usage:
  1. Download ScreenshotFolderQAPin.ps1
  2. Edit the value of $basePath for your own local ShareX screenshot folder, or whatever you'd like it to be.
  3. Run it yourself, or set it to run monthly using Task Scheduler or another program that lets you execute scripts at specific times.

Example:
Before and After

![Before running the script](https://cdn.discordapp.com/attachments/671098387768016912/791164487519830016/explorer_2020-12-22_21-44-13.png)
![After running the script](https://cdn.discordapp.com/attachments/671098387768016912/791164527898001409/unknown.png)
