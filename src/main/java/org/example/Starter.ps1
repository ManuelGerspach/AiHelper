# Stoppe alle laufenden Edge-Prozesse
Get-Process -Name "msedge" -ErrorAction SilentlyContinue | Stop-Process -Force

# Warte kurz
Start-Sleep -Seconds 2

# Starte Edge im Debugging-Modus
Start-Process "C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe" "--remote-debugging-port=9222"