#KVB Rad Positions

Dieser Service erweitert den Service [kvbradlive](https://github.com/codeforcologne/kvbradlive). Er formatiert die Ausgabe zur Darstellung z.B. auf Karten oder in Tabellen

## REST End Points

- /kvbradpositions/service/geojson; liefert alle Information im geojson - format
- /kvbradpositions/service/datatable; liefert alle Inforamationen im datatable - format
- /kvbradpositions/service/geojson/21577; liefert alle Informationen zu einem durch die nummer 
identifizierten Fahrrad

### allbikeslatestposition

Folgende REST End Points geben die letzten Standorte der Fahrräder zurück

- /kvbradpositions/service/allbikeslatestposition
- /kvbradpositions/service/allbikeslatestposition?geojson
- /kvbradpositions/service/allbikeslatestposition?datatables

## Entwicklungsstand

Dieser Service wird noch entwickelt.

## Abhänige Serivces

Dieses Projekt benötigt den Service [kvbradlive](https://github.com/codeforcologne/kvbradlive). 

## Build/ Installation

Dieses Projekt ist als Service angelegt. Das bedeutet es wird ein war-File angelegt, dass z.B. als Webapplikation durch einen Tomcat betrieben wird. Das Bauen erfolgt mit Hilfe von Maven:

mvn clean install

Dabei wird die Datei kvbradpositions.war erstellt, die in das webapp-Verzeichnis des Tomcat kopiert werden.

##Datenbank

Es wird keine Datenbank benötigt

## License

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons Lizenzvertrag" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />Dieses Werk ist lizenziert unter einer <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Namensnennung - Weitergabe unter gleichen Bedingungen 4.0 International Lizenz</a>.