# Slide review

Deck: `final-presentation.pptx`, 12 slides, 16:9 (13.33 x 7.5 in), Cambria headings and Calibri body.

## How it was checked

- Built with pptxgenjs; theme applied afterwards; file checked with the skill's `validate.py` (all validations passed).
- Rendered by Microsoft PowerPoint for Mac (PDF export) and converted to PNG at 80 dpi. LibreOffice is not installed on this machine, so PowerPoint itself was the renderer.
- All 12 slides were viewed in the first render. Slide 3 (overlapping captions and a flow row running into a note) and slide 10 (card text size) were changed and re-rendered; slide 3 was re-inspected and is clean. The other ten slides were not re-rendered after that rebuild; their code was not changed.
- Resolution of the review was 80 dpi, so small text (10 to 12 pt footers and figure captions) was judged for overlap, not for fine legibility. Please open the file once in PowerPoint before presenting.

## Findings and fixes

| Slide | Finding | Status |
|---:|---|---|
| 3 | Prompt-driven caption overlapped its flow row; "Tests" chip overlapped a note | Fixed, re-inspected |
| 10 | Finding-card text at 13 pt | Raised to 14 pt; not re-viewed at high resolution |
| 6 | Figure caption sits close to the bottom edge of the schematic window | Accepted |
| 11 | BMAD card has spare space below its three bullets | Accepted |
| 10 | Card 2 ("Impact analyses incomplete") is short | Accepted |

No text overflow, missing fonts or cut-off images were seen. Slide numbers 2 to 12 show; the title slide has none.

## Design decisions

- BMAD is always blue, Spec Kit always teal; green is used for lessons and findings, amber for CR-01, limitations and Blocked.
- Original report figures 1 to 3 are placed as images (re-rasterised at 300 dpi from the report's vector PDFs, so they are sharper than the PNGs inside the report). Everything else is native and editable, including the Figure 4 chart, tables and flow diagrams.
- The calculator on slide 6 is a drawn schematic, labelled as such. Real screenshots exist only in the Spec Kit evaluation folders, so using one would favour one track.
- `assets/extracted-charts/acceptance-outcomes.png` is the original Fig. 4, kept as the reference the native chart was checked against. It is not placed on a slide.

## Timing

Per-slide targets add up to 15:00. The scripts in the speaker notes total 2004 words, about 14.3 minutes at 140 words per minute; a slower speaking pace brings it to 15. A rehearsal is needed to confirm.
