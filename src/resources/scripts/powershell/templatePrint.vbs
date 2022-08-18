Const sPathFile = "D:\Education\Own\JAVA\Projects\LabelPrintApp\src\resources\editor\img\printing\printer.png"
Const iNormalFocus = 1
Set objShell = WScript.CreateObject("WScript.Shell")
objShell.Run "rundll32 " & chr(34) & "%ProgramFiles(x86)%\Windows Photo Viewer\PhotoViewer.dll" & chr(34) & ",ImageView_Fullscreen " & sPathFile, iNormalFocus

Wscript.Sleep 100

objShell.AppActivate "Console1"
Wscript.Sleep 1000
'OPEN PRINT PREVIEW MODAL WINDOW
objShell.SendKeys "^p"
Wscript.Sleep 1000
objShell.SendKeys "%({TAB})"
WScript.Sleep 500
objShell.SendKeys "% + { }"
WScript.Sleep 500
objShell.SendKeys "{DOWN}" & "{DOWN}" & "{DOWN}"
objShell.SendKeys "{ENTER}"
WScript.Sleep 10000
objShell.Run "taskkill /f /im rundll32.exe"

'For SHIFT prefix with +
'For CTRL  prefix with ^
'For ALT   prefix with %
