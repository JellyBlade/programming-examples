# Mass-Create AD Users
# Tyler Hippard
#
# A quick and dirty script for creating multiple users, adding them to your domain, setting their OU hierarchy, and adding them to a list of groups. 
# I used this for my networking class to quickly setup my active directory for my final project.

Write-Host "Mass-Create AD Users by Tyler Hippard"
# DC Path
$domain = Read-Host -Prompt "Input domain's FQDN (e.g. domain.company.local)"
$domainArray = $domain.Split(".")
$pathDC = ""
foreach ($DC in $domainArray) {
    $pathDC += "DC=$DC,"
}
$pathDC = $pathDC.TrimEnd(',')
$continue = 'y'
$userAmount = 1

Do {
    # Name & SamAccountName
    $username = Read-Host -Prompt "Input user #$userAmount's username"
    $userAmount++
    
    # OU Path
    $FullOU = Read-Host -Prompt "Input $username's Organizational Unity Hierarchy (e.g. OU1\NestedOU\NestedOU)"
    # Put a try catch here, maybe
    $OUArray = $FullOU.Split("\")
    [array]::Reverse($OUArray)
    $pathOU = ""
    foreach ($OU in $OUArray) {
        $pathOU += "OU=$OU,"
    }

    # AccountPassword
    $setUserPass = Read-Host -Prompt "Set Password? ([y]/n)"
    if (($setUserPass -ne 'n') -or ($setUserPass -ne 'N')) {
        $password = Read-Host -AsSecureString "Enter $username's password"
        $changePassword = Read-Host -Prompt "Change password at logon? ([y]/n)"
        if (($changePassword -ne 'n') -and ($changePassword -ne 'N')) {
            $changePassword = $true
        }
        else {
            $changePassword = $false
        }
    }
    else {
        $changePassword = $true
        $password = $Null
    }
# Groups
    Write-Host "Input group names to add $username to, enclosed in quotes and separated by commas, or leave blank for none"
    $userGroups = Read-Host -Prompt '(e.g. "Remote Desktop Users, Administrators")'
    $userGroups = $userGroups -replace '"',''
    $userGroupsArray = $userGroups.Split(",")

    # New AD User
    New-ADUser -Name $username -SamAccountName $username -AccountPassword $password -ChangePasswordAtLogon $changePassword -Server $domain -Path "$pathOU$pathDC" -Enabled $true

    Write-Host "Created user $username belonging to $domain and $FullOU OUs"

    if ($userGroups.length -gt 2) {
        foreach ($group in $userGroupsArray) {
            $groupExists = Get-ADGroup -Identity "$group"
            if ($groupExists -ne $Null) {
                Add-ADGroupMember -Identity "$group" -Members $username
            }
        }
        $groups = Get-ADPrincipalGroupMembership $username | select name
        Write-Host "$username is in the following groups:"
        Write-Host "$groups"
    }
    
    
    $continue = Read-Host -Prompt "Add another user? ([y]/n)"

} while (($continue -eq 'y') -or ($continue -eq 'Y'))