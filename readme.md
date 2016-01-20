#KVB Rad Positions

Dieser Service erweitert den Service kvbradlive. Er formatiert die Ausgabe zur Darstellung um.

## REST End Points

- /kvbradpositions/service/geojson; liefert alle Information im geojson - format
- /kvbradpositions/service/datatable; liefert alle Inforamationen im datatable - format
- /kvbradpositions/service/geojson/21577; liefert alle Informationen zu einem durch die nummer 
identifizierten Fahrrad
- /kvbradpositions/service/allbikeslatestposition/geojson;

## Entwicklungsstand

Dieser Service wird noch entwickelt.

## Bauen des Service

Dieses Projekt ist als Service angelegt. Das bedeutet es wird ein war-File angelegt, dass z.B. als Webapplikation durch einen Tomcat betrieben wird. Das Bauen erfolgt mit Hilfe von Maven:

mvn clean install

Dabei wird die Datei kvbradpositions.war erstellt, die in das webapp-Verzeichnis des Tomcat kopiert werden.

##Datenbank

Dieser Service unterstellt, dass die Datenbankverbindung per JNDI gesetzt ist. Für den Server bedeutet dies, dass der Container für die Definition der DB-Verbindung zurständig ist. Dafür muss z.B. im Tomcat die Datei context.xml angepasst werden. Folgende Einstellungen müssen eingetragen werden.

    <Resource 
      name="jdbc/kvbraeder" 
      auth="Container" 
      type="javax.sql.DataSource"
      username="username"
      password="password"
      driverClassName="org.postgresql.Driver"
      url="jdbc:postgresql://server:5432/dbname" 
      maxTotal="25" 
      maxIdle="10"
      validationQuery="select 1" />
