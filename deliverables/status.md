> Modèle de risques :
>
> Probabilité : <Basse, Moyenne, Haute>
>
> Impact : <Faible, Moyen, Elevé>
## Semaine 09
### Flag : Green
Nous avons terminé les tâches que nous avions prévu. Gatling fonctionne ainsi que le système de notifications. 
Nous sommes en train de faire des tests de charge pour voir les points faibles de notre architecture.
Nous allons sortir la dernière release dans les prochains jours après avoir testé le scénario complet.
Il nous reste à tester le scénario complet et à préparer la soutenance et la vidéo.

Risque : Le scénario complet ne marche pas
Probabilité Moyenne, impact élevé.
## Semaine 08

### Flag : Yellow
Nous allons finir la mise en place des notifications pour Vendredi prochain ainsi que la mise en place des tests Gatling, puis la livraison d'une nouvelle release.
Nous avons repoussé plusieurs fois la date de cette release en partie à cause de la charge de travail dans les autres matières, d'où le flag Yellow.

Risque : Le test de charge démontre quelques maladresses de notre système à corriger

Probabilité Élevée
Impact Faible

Risque : Le système de notification ne fonctionne pas

Probabilité Assez Faible
Impact Fort

## Semaine 07

### Flag : Green

Nous avons pu livrer la snapshot la semaine dernière dans les temps.
Ce vendredi, nous prévoyons de livrer une version 3.0 stable du système. Les enchères sont fonctionnelles et la fonctionnalité des notifications pour les fournisseurs est en cours de développement pour cette semaine.
Un test de charge très basique a été mis en place la semaine dernière, et un test de charge plus complet est prévu afin de tester un cas concret où de nombreuses enchères sont faites en même temps. Nous avons remarqué que le backend ne supportait pas toujours une charge importante de requêtes (environ une centaine en même temps). Un scénario plus réaliste, sur une période plus étalée, nous donnera une meilleure idée de la solidité de notre architecture.

Risque 1 : Se rendre compte que le backend ne supporte pas le test de charge

Probabilité Moyenne, impact élevé.

Risque 2 : Ne pas parvenir à livrer la fonctionnalité de notifications cette semaine

Probabilité Faible, impact élevé

## Semaine 06

### Flag : Yellow

Nous n'avons pas encore mis en place les notifications, la technologie n'a pas été encore trouvée. Les premiers tests ne sont pas concluants. 
Cette semaine nous prévoyons de trouver la bonne technologie de notifications et de la mettre en place dans le système.
De plus, nous comptons ajouter des tests de charge afin de générer plein d'offres d'enchères et voir comment notre système réagit si pendant ces envois, une offre a été accepté.

Risque : Prendre du retard sur la snapshot.

Probabilité Élevée
Impact Moyen

Risque : Le test de charge démontre quelques maladresses de notre système à corriger

Probabilité Élevée
Impact Faible

Risque : Le test de charge remet en question notre architecture et notre application ne supporte pas la charge

Probabilité Faible
Impact Très Fort

## Semaine 05

### Flag : Yellow

La release à mis plus de temps que prévu (livrée le 2 février). Cependant la release est fonctionnelle, un scénario supplémentaire ajoutant les bids est fonctionnel. Nous avons également ajouté des tests fonctionnels et unitaires couvrant les nouvelles US sur les enchères. Une snapshot est prévue pour ce vendredi contenant des ajustements sur le frontend ainsi que le passage à n fournisseurs. Nous allons également débuter la recherche pour mettre en place les notifications.

Risque : Prendre du retard sur la snapshot.

Probabilité Élevée
Impact Moyen

Risque : Ne pas réussir à faire fonctionner une technologie pour les notification pour la release prochaine.

Probabilité Moyenne
Impact Fort

## Semaine 04

### Flag : Green

Cette semaine, nous continuons sur notre lancée pour sortir une version stable de l'application. Cette version permettra d'agir sur les enchères (surenchérir, voir les autres offres faites sur une enchère et le responsable des maintenances pourra accepter une offre).
Nous avons également décidé d'allouer plus de ressources pour tester les fonctionnalités actuelles via des tests fonctionnels et unitaires car l'application en manque beaucoup.

Risque : Avoir une version non stable à la fin de la semaine à cause de la charge élevée de logique métier à implémenter entrainerait un retard sur la plannification des sprints suivants.

Probabilité Moyenne
Impact Moyen

## Semaine 03

### Flag : Green

Cette semaine, nous avons commencé à mettre en place en priorité les nouveaux éléments liés aux changements du sujet.
Nous avons aussi planifié une Roadmap pour ce 2ème bimestre en se séparant les tâches pour ce 1er sprint.

Le risque cette semaine est que si nous avons mal conçu l'architecture par rapport aux changements du sujet, il se peut que nous rencontrions des problèmes imprévisibles à cause des nouveaux composants, notamment pour le système d'enchère.

Probabilité : Moyenne

Impact : Elevé car il faudrait repenser l'architecture actuelle ce qui nous prendrait du temps de développement.

## Semaine 45

### Flag : Green

Nous avons terminé le développement du MVP avec quelques jours de retard par rapport à ce que l'on avais prévu.
Au cours de cette semaine, nous avons pu mettre l'accent sur le développement de la partie fonctionnelle du projet en permettant la gestion de plusieurs maintenances et plusieurs incidents. Notre MVP passe ainsi par les trois couches fonctionnelles que nous avons défini :

- gérer des maintenances,
- gérer des incidents,
- voir et accomplir les tâches associées aux incidents et maintenances).

De plus, grâce à la mise en place de la persistance la semaine dernière, nos composants n'ont plus besoin d'être stateful et notre MVP possède également toutes les couches que nous avons prévu de mettre en place :

- une couche applicative (client)
- une couche de logique métier
- une couche d'accès aux données

Le reste de cette semaine sera consacré à la retrospective de nos choix et à la préparation de la soutenance.

Il y a un risque majeur à évoquer. Nous devons encore faire en sorte que le projet se lance avec Docker pour s'assurer qu'il marche dans un environnement isolé. Pour l'instant, nous ne parvenons pas à faire en sorte que Docker se lance sur un profil de déploiement défini.

Probabilité : Moyenne au vu de la charge de travail dans les autres projets en parallèle

Impact : Elevé

## Semaine 44

### Flag : Green

Premièrement, nous avons oublié de faire le status pour ce Mardi, et nous en sommes sincèrement désolés !

Durant cette semaine, nous avons défini toutes les tâches à faire jusqu'au rendu du MVP du 06 Novembre. Nous avons mis en place la persistence avec une base de données (avec phases de TEST et PROD), des tests d'intégration avec les scénarios couvrant le MVP, en utilisant Cucumber. Il nous reste encore quelques fonctionnalités du MVP à implémenter pour étendre le système.

Nous avons prévu d'avoir un MVP fonctionnel cette semaine afin d'éviter des erreurs de dernières minutes et consacrer le temps restant à la robustesse de l'application ainsi qu'au rendu.

## Semaine 43

### Flag : Green

Lors de cette semaine nous avons commencé à mettre en place nos stack technologiques et les brancher entre elles, et nous avons prévu et réparti entre nous les tâches que nous allons faire.

Le risque, cette fois, est que si nous nous rendons compte d'un problème dans notre architecture au moment de passer à la pratique, il faudra à la fois revoir la partie de l'architecture qui pose problème, mais aussi changer le code en fonction, ce qui nous ferait prendre du retard dès le début du projet.

Probabilité : Moyen

Impact : Moyen
Pour la suite nous allons faire les tâches que nous avons défini dans notre roadmap, pour la première SNAPSHOT

## Semaine 42

### Flag : Green

Cette semaine nous avons déroulé des scénarios ce qui nous a permis de préciser notre diagramme de composant (notamment les méthodes des interfaces). Nous avons également choisi les technologies que nous allons utiliser pour le frontend et le backend.

Le risque actuel est le choix de la technologie qui pourrait être difficile à prendre en main et entrainerait du retard.

Pour la suite nous allons inititaliser le front et le back avec les technologies définies et commencer à faire communiquer les deux. Une fois cette étape terminée nous commencerons à implémenter le MVP.

## Semaine 41

### Flag : Green

Cette semaine, nous avons défini un diagramme de composant couvrant le scope complet du projet ainsi qu'un autre diagramme de composant couvrant le scope de notre MVP.
L'unique risque cette semaine est le fait que l'on a peut être oublié certains détails particuliers du scénario dans les diagrammes. Il est donc nécessaire de bien passer en revu tous les scénarios en suivant le cheminement de l'information à travers le diagramme de composant.
Nous n'avons pour l'instant rencontré aucun point bloquant particulier.
Nous prévoyons par la suite de définir les besoins techniques du projet, et ainsi choisir en conséquence les technologies que nous allons utiliser (langages, frameworks, etc.).

## Semaine 40

### Flag : Green

Durant cette semaine, nous avons pu établir un scope global du projet, ainsi qu'un scope moins détaillé pour le MVP, comprendre quel est le rôle de la partie du système que nous allons développer et ainsi décrire des persona et des scénarios qui permettent d'utiliser les fonctions basique du système.

Pour la semaine prochaine, nous allons rentrer un peu plus dans les détails techniques et définir un diagramme de composant pour bien séparer le code métier.
