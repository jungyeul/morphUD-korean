dataset naming convention:
- ko_gsd-ud-{train|dev|test}.conllu: wordUD
- ko_gsd-ud-{train|dev|test}-morphUD.conllu: morphUD
- ko_gsd-ud-{train|dev|test}-+morphUD.conllu: +morphUD
- ko_gsd-ud-test-{udpipe|uuparse|stanza}-parsed.conllu: {udpipe|uuparse|stanza} predicted wordUD
- ko_gsd-ud-test-morphUD-{udpipe|uuparse|stanza}-parsed-converted2wordUD.conllu: {udpipe|uuparse|stanza} predicted morphUD, converted to wordUD
- ko_gsd-ud-test-+morphUD-{udpipe|uuparse|stanza}-parsed-converted2wordUD.conllu: {udpipe|uuparse|stanza} predicted +morphUD, converted to wordUD for comparison
