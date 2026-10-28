# Methodology References

**Checked:** 2026-10-25. **Status:** Targeted reference register for team verification.

These sources inform our design; they do not validate our exact rubric or establish results for either calculator. Reading scope is stated to avoid implying full-paper review where only an abstract was checked. Source selection is described in [methodology-review.md](methodology-review.md).

## M1 — Case-study procedure and validity

P. Runeson and M. Höst, “Guidelines for conducting and reporting case study research in software engineering,” *Empirical Software Engineering*, vol. 14, pp. 131–164, 2009. DOI: `10.1007/s10664-008-9102-8`.

- [Publisher full text](https://link.springer.com/article/10.1007/s10664-008-9102-8).
- **Type/read scope:** Peer-reviewed methodological article; abstract and relevant full-text sections, especially §§2.4, 3.2, 5.2.3, and 6.
- **Use:** Protocol versioning, evidence organisation, and validity assessment.
- **Limit:** Case-study guidance does not make our educational comparison a randomised experiment. The article's validity categories include reliability; our separate conclusion-validity treatment is a project choice.

## M2 — Measurement and collection quality

B. A. Kitchenham, S. L. Pfleeger, L. M. Pickard, P. W. Jones, D. C. Hoaglin, K. El Emam, and J. Rosenberg, “Preliminary guidelines for empirical research in software engineering,” *IEEE Transactions on Software Engineering*, vol. 28, no. 8, pp. 721–734, 2002.

- [Full paper, university-hosted copy](https://www.cin.ufpe.br/~in1002/leituras/preliminaryGuidelinesEmpiricalResearchSE.pdf).
- **Type/read scope:** Peer-reviewed methodological article; bibliographic header, abstract, and relevant measurement/data-collection passages, particularly §4.2, DC1–DC3.
- **Use:** Explicit measurement definitions, collection checks, and attention to reviewer agreement.
- **Limit:** Broad preliminary guidance, predating modern coding agents; our intervention categories, units, and scoring anchors are adaptations.

## M3 — Empirical reporting standards

ACM SIGSOFT, *Empirical Standards for Software Engineering Research*, “General Standard” and “Case Study and Ethnography,” online resource, accessed 2026-10-25.

- [Official standards](https://www2.sigsoft.org/EmpiricalStandards/docs/standards).
- **Type/read scope:** Professional methodological guidance; General Standard and case-study applicability/attributes.
- **Use:** Explicit methods, data collection, analysis, limitations, and evidence chains.
- **Limit:** Not a peer-reviewed result about SDD. The case-study standard's real-site condition cautions against treating a small calculator example as an industrial case study. We do not claim full compliance.

## M4 — Requirements traceability

O. C. Z. Gotel and A. C. W. Finkelstein, “An analysis of the requirements traceability problem,” in *Proceedings of the First International Conference on Requirements Engineering*, 1994, pp. 94–101.

- [UCL institutional record and abstract, with full-text link](https://discovery.ucl.ac.uk/id/eprint/749/).
- **Type/read scope:** Conference paper; institutional bibliographic record and abstract checked. Full paper not reviewed for this draft.
- **Use:** The distinction between pre-specification and post-specification traceability motivates retaining clarification decisions as well as downstream links.
- **Limit:** Does not establish our coverage formula, rating scale, or the quality of either modern workflow.

## M5 — Context for AI-assisted development evaluation

S. Peng, E. Kalliamvakou, P. Cihon, and M. Demirer, “The Impact of AI on Developer Productivity: Evidence from GitHub Copilot,” arXiv:2302.06590, 2023. DOI: `10.48550/arXiv.2302.06590`.

- [Primary arXiv record, version 1](https://arxiv.org/abs/2302.06590v1).
- **Type/read scope:** Preprint; metadata and abstract checked. No claim of peer review or full methods review.
- **Use:** Example of a controlled AI-development comparison with a specific task and completion-time outcome.
- **Limit:** Copilot-assisted JavaScript server development differs from our two SDD workflows. No effect size from that study is transferred to our calculator, and its abstract cannot establish detailed collection procedures for us.

## Verification and AI-use note

Primary publications, institutional records, and official guidance were consulted through web search. No systematic search, database screening, or full review of every paper is claimed. AI assisted source discovery and drafting. Before submission, team members should verify citations and adopted claims, update reading scope if they read more, and incorporate the actual use into the required AI-use declaration.
