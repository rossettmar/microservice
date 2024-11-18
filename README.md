Questo microservizio utilizza un immmagine di RabbitMQ rilasciata sul kebernates locale gestito da rancher desktop.

Per predisporre l'ambiente keubernates eseguire le seguenti operazioni, dopo aver avviato rancher desktop:

1) Creare un immagine docker eseguendo il file docker-compose contenuto nella cartella 'resources' del progetto
   Posizionarsi, tramite la shell della versione linux che si è collegata a rancher desktop, nella cartella 'resources' ed eseguire:

   docker-compose up -d

   (in alternativa, si puà utilizzare l'ambiente rancher desktop per fare direttamente la pull da docker hub dell'immagine 'rabbitmq:3.8-management-alpine')
   
3) Verificare che l'immagine sia stata correttamente creata in rancher desktop dall'interfaccia 'images' di rancher

4) Eseguire il seguente comando dalla shell linux:

   kubectl run rabbitmq --image=rabbitmq:3.8-management-alpine --image-pull-policy=Never --port=5672

6) Eseguire il seguente comando dalla shell linux:

   kubectl port-forward pods/rabbitmq 5672:5672 15672:15672

A questo punto è stato creato un pod attivo sulla macchina locale con una versione running di RabbitMQ

7) Per verificare che rabbitMQ sia effettivamente up & running collegarsi al seguente indirizzo:

   localhost:15672

8) Immettere 'guest' nella casella user e nella casella password

Se si accede all'interfaccia di gestione di RabbitMQ l'ambiente è correttamente configurato e si può procedere all'avvio del microservizio

9) Verificata il corretto start del microservizio si può testare l'invio del messaggio andando a chiamare il controller di prova:

   localhost:8080/api/v1/publish?message=Hello world!

A questo punto il producer del microservizio dovrebbe aver creato la coda, il topic di exchange e la chiave, e inviato il primo messaggio in coda, 
contenente un payload di tipo String con il testo 'Hello world!'. Il tutto dovrebbe essere verificabile e consultabile nella console di controllo 
di RabbitMQ
   
   
