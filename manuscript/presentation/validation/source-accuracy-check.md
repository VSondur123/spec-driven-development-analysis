# Source accuracy check

Source: `manuscript/final-report/final-report.pdf`, read in full as rendered pages (all 10 pages, including Figures 1 to 4 and Tables I to VIII). Each item below was compared by hand against the report text; no automated cross-check was run.

## Numbers and facts

| Slide | Item on slide | Report location | Result |
|---:|---|---|---|
| 4 | RQ1 to RQ4 wording; G1 to G5 foci; G4 and G5 supporting | Sec. III-A, Table I | Matches (RQ wording shortened) |
| 5 | 13 requirements (8 functional, 5 non-functional), 19 criteria | Sec. III-B, Table II | Matches |
| 5 | CR-01: 11 requirements, 22 criteria, 19 baseline re-checks | Table II | Matches |
| 5 | Fairness rules (six) | Sec. III-C | Matches |
| 6 | Exact decimals, half-up to ten places, error priority, unary operations use first field | Sec. III-B | Matches |
| 6 | Operation symbols + - x / on the schematic | Not stated as symbols in the report | Drawn from "four operations"; labelled schematic |
| 7 | BMAD 6.12.4; fast path; UX skipped; 3 epics, 9 stories; Epic 4 with four stories | Sec. IV-A, Table III, Fig. 2 | Matches |
| 7 | Review layers: 3; two stories on a minimal path | Sec. IV-A | Matches |
| 8 | specify-cli 1.2.3; 34 baseline tasks; 11 findings (2 high, 2 medium, 7 low); 49 tasks; 10 findings; constitution 1.0.0 to 1.1.0 | Sec. IV-B, Fig. 3 | Matches |
| 9 | Git tags, four statuses, three evidence layers, 16 measures, not-calculable list | Sec. III-C, III-D, Table VI | Matches |
| 10 | BMAD 19, 19, 22, 19; Spec Kit 19, 18, 22, 19 | Fig. 4, Sec. V-B, V-C | Matches |
| 10 | Independent baseline: BMAD 19 Pass; Spec Kit 18 Pass, 0 Fail, 1 Blocked | Sec. V-B | Matches |
| 10 | Later human-reported follow-up marked Pass; Blocked stays visible | Sec. V-C | Matches |
| 11 | Threats to validity (eight listed) | Sec. VI-E, V-C | Matches |
| 11 | Five practical lessons | Sec. VI-D | Matches (shortened) |
| 12 | Conclusion statement; BMAD and Spec Kit points; no overall ranking | Sec. VII | Matches |

## Deviations from the brief, for the owner to confirm

1. **Labels on slide 10.** The brief calls the CR-01 and regression figures "operator-reported". The report calls them "reported statuses" and says the BMAD change results sit in evaluator sheets and the 22/19 figures are combined reported statuses. The slide says "reported" for those two groups and "operator" and "independent" only for the two baseline groups.
2. **Figure 4 shows operator and independent baseline results side by side.** This is how the report draws it, with each group labelled. The brief asked not to merge evidence layers into one score; no score or total is computed, but the chart does sit them in one frame.
3. **"Both impact analyses missed some affected elements"** from the brief is worded as "met effects their pre-coding analysis had not listed", which is the report's wording.
4. **Slide 8 and 7 change paths.** Flows are condensed from the report text (Sec. IV); "Owner approved Epic 4 before the plan was amended" paraphrases the Epic 4 approval described there.
5. **Spec Kit change path** shows "New feature: spec, plan" for "new feature folder with its own spec, plan and tasks".

## Not verified

- Presenter-to-slide mapping and timings follow the brief and were not checked against anything in the report.
- Speaking time was estimated from word counts, not rehearsed.
- Speaker notes contain statements taken from the report; they were not re-read line by line against the PDF beyond the table above.
