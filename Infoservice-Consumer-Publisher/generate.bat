rd /s /q generate
md generate\src

wsimport -d bin -s generate/src -keep http://localhost:9090/infoservice?wsdl