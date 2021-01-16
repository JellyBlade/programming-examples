# Monthly ShareX Screenshot Folder Quick Access Pin
# By Tyler Hippard, who is god-awful at naming his scripts.
# 
# Requires: Task Scheduler (or an alternative), Windows
#
# Usage: Change $basePath to match your folder layout, and setup a monthly task in Task Scheduler (or alternative) to run on the first of every month.
#        This script will pin the current month's screenshot folder to Quick Access, and unpin the previous month.
#
# Designed for use with ShareX's local screenshot folders, but could be used for any monthly-updating folders that use a YYYY-MM date format for naming.
# Because this script creates the folder if it doesn't exist, you could even use it to make YYYY-MM dated folders for some purpose.

# For people wanting to use this script, edit this line below to be the directory of your ShareX screenshot folder.
$basePath = "C:\Path\To\Your\ShareX Screenshots\Folder"

$currentMonth = Get-Date -Format "yyyy-MM"
$lastMonth = Get-Date (Get-Date).AddMonths(-1) -Format "yyyy-MM"

$currentMonthPath = "$basePath\$currentMonth"
# Create folder if not yet exists
if (!(Test-Path $currentMonthPath)) {
    New-Item -ItemType Directory -Force -Path $currentMonthPath
}

$lastMonthPath = "$basePath\$lastMonth"

# Add new folder
$newFolder = New-Object -ComObject shell.application
$newFolder.NameSpace("$currentMonthPath").Self.InvokeVerb("pintohome")

# Remove old folder

$remove = New-Object -ComObject shell.application ($remove.NameSpace("shell:::{679F85CB-0220-4080-B29B-5540CC05AAB6}").Items() | Where-Object { $_.Path -eq "$lastMonthPath" }).InvokeVerb("unpinfromhome")
