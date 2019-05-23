#Make file for space explorers games
GUINAME = rvo16_hoo42_SpaceExplorersGUI.jar
CLINAME = rvo16_hoo42_SpaceExplorersCLI.jar

.PHONY: help
help:
	@echo 'Usage: (make type=gui/cli name=filename) | (make gui) | (make cli)'

all:
ifeq ($(type),gui)
	ant -f Build.xml -Dname $(name) -Dmain graphicalInterface.StartApplication
	chmod +x $(name)
else
ifeq ($(type),cli)
	ant -f Build.xml -Dname $(name) -Dmain commandLine.GameEnviroment
	chmod +x $(name)
else
	make help
endif
endif

GUI:
	ant -f Build.xml -Dname $(GUINAME) -Dmain graphicalInterface.StartApplication
	chmod +x $(GUINAME)


CLI:
	ant -f Build.xml -Dname $(CLINAME) -Dmain commandLine.GameEnviroment
	chmod +x $(CLINAME)
