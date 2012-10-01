package br.com.fsilveira.finance.faces.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.fsilveira.finance.business.AbstractBusiness;
import br.com.fsilveira.finance.entity.AbstractEntity;
import br.com.fsilveira.finance.faces.AbstractBean;

public class ImportUtils<T extends AbstractEntity> extends AbstractBean {

	private List<ImportResults> results;
	private ImportResults importResultsSelected;
	private AbstractBusiness<T> business;

	public ImportUtils(AbstractBusiness<T> business) {
		this.results = new ArrayList<ImportResults>();
		this.business = business;
	}

//	public void doImport(FileUploadEvent event) {
//		UploadedFile file = event.getUploadedFile();
//
//		InputStream is = null;
//
//		try {
//
//			is = file.getInputStream();
//
//			final ImportResults importResults = business.doUploadFile(is);
//
//			importResults.setFile(file);
//
//			if (results.size() == 5) {
//				results.remove(0);
//			}
//
//			results.add(importResults);
//
//		} catch (BusinessException e) {
//			getMessages().addExceptionMessage(e);
//
//		} catch (DAOException e) {
//			getMessages().addExceptionMessage(e);
//
//		} catch (ResultadoNaoUnicoException e) {
//			getMessages().addExceptionMessage(e);
//
//		} catch (FileImportException e) {
//			getMessages().addExceptionMessage(e);
//		} catch (IOException e) {
//			getMessages().addMessage(FacesMessage.SEVERITY_ERROR);
//		} finally {
//			CloseableUtils.close(is);
//		}
//
//	}

	public List<ImportResults> getResults() {
		return results;
	}

	public void setResults(List<ImportResults> results) {
		this.results = results;
	}

	public ImportResults getImportResultsSelected() {
		return importResultsSelected;
	}

	public void setImportResultsSelected(ImportResults importResultsSelected) {
		this.importResultsSelected = importResultsSelected;
	}

}
