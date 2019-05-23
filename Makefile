#Make file for space explorers games
GUINAME = rvo16_hoo42_SpaceExplorersGUI.jar
CLINAME = rvo16_hoo42_SpaceExplorersCLI.jar
.PHONY: gui
gui:
	ant -f Build.xml -Dname $(GUINAME) -Dmain graphicalInterface.StartApplication
	chmod +x $(GUINAME)

.PHONY: cli
cli:
	ant -f Build.xml -Dname $(CLINAME) -Dmain commandLine.GameEnviroment
	chmod +x $(CLINAME)
