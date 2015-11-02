#KVB Rad Positions

Dieser Service fragt in Regelmäßigen Abständen (etwa alle 5 min) die Positionen der KVB-Fahrräder ab. Er speichert nur die Informationen je Fahrrad. Nur die Räder werden gespeichert, die gerade nicht verwendet werden. Die Position eines Rades wird nur dann abgespeichert, wenn sich die Position geändert hat.

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
