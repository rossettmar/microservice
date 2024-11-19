-- INSTALLAZIONE RABBITMQ CON ESEMPIO SERVIZIO PRODUCER --

Questo microservizio utilizza un'immmagine di RabbitMQ rilasciata sul kubernates locale gestito da rancher desktop.

Per predisporre l'ambiente kebernates eseguire le seguenti operazioni, dopo aver avviato rancher desktop:

1) Creare un'immagine docker eseguendo il file docker-compose contenuto nella cartella 'resources' del progetto
   Posizionarsi tramite la shell della versione linux che si è collegata a rancher desktop nella cartella 'resources' ed eseguire:

   docker-compose up -d

   (in alternativa, si può utilizzare rancher desktop per fare direttamente la pull da docker hub dell'immagine 'rabbitmq:3.8-management-alpine')
   
3) Verificare che l'immagine sia stata correttamente creata in rancher desktop dall'interfaccia 'images' di rancher desktop

4) Eseguire il seguente comando dalla shell linux:

   kubectl run rabbitmq --image=rabbitmq:3.8-management-alpine --image-pull-policy=Never --port=5672

5) Eseguire il seguente comando dalla shell linux:

   (shell linux su windows)
   kubectl port-forward pods/rabbitmq 5672:5672 15672:15672

   (shell linux su linux - necessario cambiare porta)
   kubectl port-forward pods/rabbitmq 5673:5672 15673:15672

A questo punto è stato creato un pod attivo sul contenitore kubernates della macchina locale con RabbitMQ.

6) Per verificare che rabbitMQ sia effettivamente up & running collegarsi al seguente indirizzo:

   localhost:15672 (o 15673 su linux)

7) Immettere 'guest' nella box user e in quella password

Se si accede all'interfaccia di gestione di RabbitMQ l'ambiente è correttamente configurato e si può procedere all'avvio del microservizio

8) Avviato il micorservizio da IntellijIdea e verificato il corretto start si può testare l'invio del messaggio andando a chiamare il controller di prova:

   localhost:8080/api/v1/publish?message=Hello world!

A questo punto il producer del microservizio dovrebbe aver creato la coda, il topic di exchange e la chiave, e pubblicato il primo messaggio, 
contenente un payload di tipo String con il testo 'Hello world!'. Il tutto dovrebbe essere verificabile e consultabile nella console di controllo 
di RabbitMQ.
   
-- CREAZIONE CONSUMER --

Fare riferimento alla classe RabbitMQConsumer nel progetto
