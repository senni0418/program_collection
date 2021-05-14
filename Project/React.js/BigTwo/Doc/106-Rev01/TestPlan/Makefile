all: TestPlan.pdf

MAIN = TestPlan
TEX_SOURCES = Makefile \
	$(MAIN).tex \


TestPlan.pdf: $(TEX_SOURCES)
	pdflatex TestPlan

clean:
	rm -f $(MAIN).{pdf,log,blg,bbl,aux,out,toc,lof,lot,idx,bcf,run.xml,ind,ilg,fls,fdb_latexmk}