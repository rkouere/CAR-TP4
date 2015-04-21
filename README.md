
Creation d'une application en utilisant jEE
Congcong Xu - Nicolas Echallier
21/04/14

====================================================
Introduction :
Le but de ce TP est de crééer une application utilisant Java EE et simulant un site de vente de livre.
Il est possible d'accéder au differente partie du TP en utilisant le menu.

Toutes les implémentation demandé ont été effectueé mise a part la derniere :
Q2
Q1
O2.1 OK avec un simple formulaire html (cf : form.html).
Pour info, le formulaire verifie que toutes les données ont bien été remplies.

Q2.22
OK avec un simple JSP (cf : formDealer.jsp).

Q2.33
- changement du formDealer.jsp pour rajouter les parametres dans une session
- utilisation d'un JSP pour afficher le formulaire. Si des elements sont presents dans la session, il les affiches.

Q3.1
Ok, voir Books.java

Q3.2
OK, voir :
- BooksFacadeLocal.java

Q3.3
OK, voir
- GetListAuthors.java
- GetListBooks.java


Q3.4
OK, voir
- addBook.java

Q3.5
Il est possible d'ajouter/supprimer des livres dans un panier. L'utilisateur ne peut voire la liste que s'il est loggé. Lorsque l'utilisateur a fini ses achats, il peut les confirmer en se rendant sur la page de checkout (Purchase). Il peut ensuite soit valider ses achats soit reseter la liste.
Il est possible de se loger au service. Lorsque l'on se log, nous pouvons ajouter un livre à la base de donnée.
Il est aussi possible d'ajouter un utilisateur au service




====================================================
Utilisation
Importer le projet dans Netbeans et le lancer

====================================================
Test :
Nous avons essayé de mettre en place des tests mais sans succès. Nous n'avons pas compris comment les mettres en place (voir le dossier /test pour les essais).

====================================================
Gestion d'erreurs
Nous avons géré les erreurs avec les exceptions par défault.

====================================================
Code Samples
Il est demandé dans le rendu de présenter 5 codes samples interessant. Ce rendu de TP n'a pas implémenté de code avec soit une méthode contenant un algorithme intéressant, soit une liste de classes impliquées dans un design pattern soit une jolie optimisation. Je ne savais donc pas quoi mettre dans cette section.
