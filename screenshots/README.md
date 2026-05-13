# Screenshots — noms attendus

## Partie AE
| Fichier                      | Quand le prendre                                      |
|------------------------------|-------------------------------------------------------|
| AE_01_email_rouge.png        | mvn test -Dtest=UtilisateurTabTest  (avant validation) |
| AE_01_email_vert.png         | mvn test -Dtest=UtilisateurTabTest  (après validation) |
| AE_02_moyenne_rouge.png      | mvn test -Dtest=CalculTabTest       (avant moyenne)    |
| AE_02_moyenne_vert.png       | mvn test -Dtest=CalculTabTest       (après moyenne)    |
| AE_03_mediane_rouge.png      | mvn test -Dtest=CalculTabTest       (avant médiane)    |
| AE_03_mediane_vert.png       | mvn test -Dtest=CalculTabTest       (après médiane)    |
| AE_04_scenarios_rouge.png    | mvn test -Dtest=ScenarioGroupeTPTest (avant toResultat)|
| AE_04_scenarios_vert.png     | mvn test -Dtest=ScenarioGroupeTPTest (après toResultat)|
| AE_05_final_vert.png         | mvn test  (tous les 25 tests au vert)                  |

## Partie LC
| Fichier                      | Quand le prendre                                      |
|------------------------------|-------------------------------------------------------|
| LC_01_validation_rouge.png   | mvn test -Dtest=CalculTabEdgeCasesTest (avant valid.)  |
| LC_01_validation_vert.png    | mvn test -Dtest=CalculTabEdgeCasesTest (après valid.)  |
| LC_02_stockage_rouge.png     | mvn test -Dtest=ResultatStockageTest  (avant classe)   |
| LC_02_stockage_vert.png      | mvn test -Dtest=ResultatStockageTest  (après classe)   |
| LC_03_final_vert.png         | mvn test  (tous les tests finaux au vert)              |

## Conseils
- Prendre le screenshot en plein écran terminal ou dans IntelliJ
- Bien visible : le résultat "Tests run: X, Failures: Y"
- Résolution correcte pour que le texte soit lisible dans le PDF
