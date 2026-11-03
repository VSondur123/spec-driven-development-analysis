# Methodology References

**First checked:** 2026-10-25. **Rechecked:** 2026-11-03. **Revision:** draft-5.
**Status:** Targeted reference register; AI-assisted checks completed to the stated scope, human/team confirmation pending.

These sources inform our design; they do not validate our exact rubric or establish results for either calculator. Reading scope is stated to avoid implying full-paper review where only an abstract was checked. Source selection is described in [methodology-review.md](methodology-review.md).

Source IDs M1–M6 are separate from metric IDs M01–M16. M6 below is the same work as Thanh's L5; retain both members' existing IDs when cross-referencing. No source is labelled fully read by a human on the basis of AI-assisted retrieval.

## M1 — Case-study procedure and validity

P. Runeson and M. Höst, “Guidelines for conducting and reporting case study research in software engineering,” *Empirical Software Engineering*, vol. 14, pp. 131–164, 2009. DOI: `10.1007/s10664-008-9102-8`.

- [Publisher full text](https://link.springer.com/article/10.1007/s10664-008-9102-8).
- **Type/read scope:** Peer-reviewed methodological article; abstract and relevant full-text sections, especially §§2.4, 3.2, 5.2.3, and 6.
- **2026-11-03 check:** Publisher metadata and selected full-text passages in §§3.2 and 5.2–5.2.3 rechecked. The earlier draft recorded broader selected-section reading; this revision does not claim a new complete reading. The journal citation year is 2009, distinct from online publication in 2008.
- **Use:** Protocol versioning, evidence organisation, and validity assessment.
- **Limit:** Case-study guidance does not make our educational comparison a randomised experiment. The article's validity categories include reliability; our separate conclusion-validity treatment is a project choice.

## M2 — Measurement and collection quality

B. A. Kitchenham, S. L. Pfleeger, L. M. Pickard, P. W. Jones, D. C. Hoaglin, K. El Emam, and J. Rosenberg, “Preliminary guidelines for empirical research in software engineering,” *IEEE Transactions on Software Engineering*, vol. 28, no. 8, pp. 721–734, 2002. DOI: [10.1109/TSE.2002.1027796](https://doi.org/10.1109/TSE.2002.1027796).

- [Full paper, university-hosted copy](https://www.cin.ufpe.br/~in1002/leituras/preliminaryGuidelinesEmpiricalResearchSE.pdf).
- **Type/read scope:** Peer-reviewed methodological article; bibliographic header, abstract, and relevant measurement/data-collection passages, particularly §4.2, DC1–DC3.
- **2026-11-03 check:** University-hosted primary paper header and §4.2 DC1–DC3 rechecked; DOI also appears in M1's reference list. These passages concern units/counting rules, subjective measurement and collection quality controls.
- **Use:** Explicit measurement definitions, collection checks, and attention to reviewer agreement.
- **Limit:** Broad preliminary guidance, predating coding agents; our categories/anchors are adaptations. DC2 discusses agreement statistics; our small study's descriptive exact agreement is a disclosed local choice, not the statistic prescribed by that passage.

## M3 — Empirical reporting standards

ACM SIGSOFT, *Empirical Standards for Software Engineering Research*, “The General Standard,” “Case Study and Ethnography,” and “Multi-Methodology and Mixed Methods Research,” online resource, accessed 2026-11-03 (earlier check: 2026-10-25).

- [Official standards](https://www2.sigsoft.org/EmpiricalStandards/docs/standards).
- **Type/read scope:** Professional methodological guidance; General essential attributes, Case Study applicability, and Mixed Methods essential attributes/antipatterns checked on 2026-11-03. Not a full review of every standard.
- **Use:** Reporting evidence and limitations; justifying and integrating quantitative/qualitative findings in the RQ-level findings table.
- **Limit:** Not an empirical SDD result. A case-study site can be a team or process; the standard does not require an industrial company. Our educational task's realism remains limited, and we claim no full compliance. The exact integration table is our adaptation.

## M4 — Requirements traceability

O. C. Z. Gotel and A. C. W. Finkelstein, “An analysis of the requirements traceability problem,” in *Proceedings of the First International Conference on Requirements Engineering*, 1994, pp. 94–101.

- [UCL institutional record and abstract, with full-text link](https://discovery.ucl.ac.uk/id/eprint/749/).
- **Type/read scope:** Conference paper; institutional bibliographic record and abstract checked. Full paper not reviewed for this draft.
- **2026-11-03 check:** UCL record reconfirms authors, title, year, proceedings and pp. 94–101; abstract's pre-/post-requirements-specification distinction rechecked. No full-paper reading is added.
- **Use:** The distinction between pre-specification and post-specification traceability motivates retaining clarification decisions as well as downstream links.
- **Limit:** Does not establish our coverage formula, rating scale, or the quality of either modern workflow.

## M5 — Context for AI-assisted development evaluation

S. Peng, E. Kalliamvakou, P. Cihon, and M. Demirer, “The Impact of AI on Developer Productivity: Evidence from GitHub Copilot,” arXiv:2302.06590, 2023. DOI: `10.48550/arXiv.2302.06590`.

- [Primary arXiv record, version 1](https://arxiv.org/abs/2302.06590v1).
- **Type/read scope:** Preprint; metadata and abstract checked. No claim of peer review or full methods review.
- **2026-11-03 check:** Version-1 arXiv authors/title, 13 February 2023 submission date, identifier/DOI and abstract rechecked. This citation remains the 2023 preprint version; no later publication status is inferred.
- **Use:** Example of a controlled AI-development comparison with a specific task and completion-time outcome.
- **Limit:** Copilot-assisted JavaScript server development differs from our two SDD workflows. No effect size from that study is transferred to our calculator, and its abstract cannot establish detailed collection procedures for us.

## M6 — GQM measurement structure (also Thanh L5)

V. R. Basili, G. Caldiera, and H. D. Rombach, “Goal Question Metric Approach,” in *Encyclopedia of Software Engineering*, John Wiley & Sons, 1994, pp. 528–532.

- [Author's publication list, item T89](https://www.cs.umd.edu/~basili/papers.html), [author-hosted reprint](https://www.cs.umd.edu/~basili/publications/technical/T89.pdf), and [readable university-hosted author copy](https://www.cs.umd.edu/~mvz/handouts/gqm.pdf).
- **Type/read scope:** Scholarly reference-work chapter, not a journal article or an SDD experiment. On 2026-11-03, the author's T89 record was checked for authors, title, year, publisher and published page range; §§1–2 of the readable copy were consulted for the goal/question/metric hierarchy and contextual interpretation. No complete-chapter reading or separately verified peer-review process is claimed.
- **Version note:** Online copies have different pagination/titling presentations. Published pp. 528–532 come from T89, not PDF page numbers. Cite the readable copy's section numbers for consulted passages. No DOI or volume/edition detail is invented.
- **Use:** Supports organising measurement by goals and questions. Our crosswalk reuses RQ1–RQ5 and Thanh's proposed M01–M16, pending joint review.
- **Limit:** GQM does not establish the validity of our metric formulas, local qualitative anchors, metric tiers or comparative conclusions. This update does not change Thanh's L5 reading-status entry; he can reconcile it using the primary links above.

## Decision-to-source map

| Methodological decision / concept | Supporting sources | Local adaptation / evidence boundary |
|---|---|---|
| Educational comparative design and bounded claims | M1; M3 Case Study applicability | Two workflow runs on one calculator; no industrial representativeness or tool-only causal estimate |
| Quantitative/qualitative integration | M3 Mixed Methods section; M1 §5.2 | Structured artefact review and an evidence-linked RQ findings table; numeric ratings alone are insufficient |
| Goals → questions → measures | M6 / Thanh L5 | Proposed alignment of existing questions/metric IDs, not an adopted replacement GQM model |
| Collection protocol, counting rules and review | M1 §3.2; M2 §4.2 | Checklist items, episode units, 19-parent aggregation and J1–J7 are project proposals |
| Clarifications and downstream traceability | M4 abstract | Native versus reconstructed counting is a local operational distinction |
| Validity, evidence gaps and reproducibility | M1 §5.2.3; M2; M3 General | Four validity categories plus reliability follow the project brief; exact-agreement and missing-data rules are local |
| AI-study context | M5 abstract | Illustrative contrast only; no expected effect size or detailed procedure inferred |

## Verification and AI-use note

Primary publications, institutional records, and official guidance were consulted through web search and direct retrieval. On 2026-11-03, AI assisted metadata/passage verification, GQM alignment and drafting. Checks were limited to the scopes above; inaccessible machine-readable text in the T89 scan was supplemented by the readable primary author copy. No systematic search, database screening or complete review of every source is claimed. Before submission, team members should verify the adopted claims, record their own reading scope and include actual assistance in the course's AI-use declaration. Preserve prior checking dates rather than presenting this refresh as pre-implementation reading.
