$Count = 200
$Names = Invoke-RestMethod "https://randommer.io/Name" -Body "type=fullname&number=$Count&X-Requested-With=XMLHttpRequest" -Method Post

$Users = @{"recipients" = @() }

for ($i = 0; $i -lt $Count; $i++) {
    $Users.recipients += [PSCustomObject]@{
        "name"        = $Names[$i].Trim()
        "phoneNumber" = "601" + (Get-Random 2, 4, 6, 8, 9) + (Get-Random -Minimum 100  -Maximum 999) + (Get-Random -Minimum 1000 -Maximum 9999)
        "status"      = Get-Random -Minimum 1 -Maximum 5
    }
}

$Users | ConvertTo-Json | Out-File "recipients.json"