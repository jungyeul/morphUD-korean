# An alternative format of morpheme-based Universal Dependencies (morphUD) for Korean

- src: a script folder
- dataset: a dataset folder and their parsing results including morphUD, +morphUD and wordUD (original). morphUD and +morphUD datasets include their parsing results in the form of wordUD (for "fair" comparison).


dataset naming convention:
- ko_gsd-ud-{train|dev|test}.conllu: wordUD
- ko_gsd-ud-{train|dev|test}-morphUD.conllu: morphUD
- ko_gsd-ud-{train|dev|test}-+morphUD.conllu: +morphUD
- ko_gsd-ud-test.parsed.conllu: predicted wordUD
- ko_gsd-ud-test-morphUD-converted2wordUD.parsed.conllu: predicted morphUD, converted to wordUD for comparison
- ko_gsd-ud-test-+morphUD-converted2wordUD.parsed.conllu: predicted +morphUD, converted to wordUD for comparison



