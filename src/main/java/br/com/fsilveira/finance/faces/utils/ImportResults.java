package br.com.fsilveira.finance.faces.utils;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import br.com.fsilveira.finance.faces.utils.messagesI18N.MessageKey;

public class ImportResults {

	//private UploadedFile file;
	private Integer importedRecords = 0;
	private Integer duplicatedRecords = 0;
	private Integer invalidRecords = 0;
	private boolean invalid;
	private long fileSize;
	private DateTime dataImport;

	private List<MessageKey> invalidMessages = new ArrayList<MessageKey>();
	private List<MessageKey> duplicatedMessages = new ArrayList<MessageKey>();
	private List<MessageKey> importedMessages = new ArrayList<MessageKey>();
	
	

	public ImportResults() {
		super();
		this.setDataImport(DateTime.now());
	}

	private void addImportedRecord() {
		importedRecords++;
	}

	private void addDuplicatedRecord() {
		duplicatedRecords++;
	}

	private void addInvalidRecord() {
		invalidRecords++;
	}

	public void addDuplicatedMenssage(MessageKey message) {
		addDuplicatedRecord();
		duplicatedMessages.add(message);
	}

	public List<MessageKey> getDuplicatedMessages() {
		return duplicatedMessages;
	}

	public void addImportedMessage(MessageKey message) {
		addImportedRecord();
		importedMessages.add(message);
	}

	public List<MessageKey> getImportedMessages() {
		return importedMessages;
	}

	public void addInvalidMenssage(MessageKey message) {
		addInvalidRecord();
		setInvalid(true);
		invalidMessages.add(message);
	}

	public List<MessageKey> getInvalidMessages() {
		return invalidMessages;
	}

	public Integer getImportedRecords() {
		return importedRecords;
	}

	public Integer getDuplicatedRecords() {
		return duplicatedRecords;
	}

	public void setDuplicatedRecords(Integer duplicatedRecords) {
		this.duplicatedRecords = duplicatedRecords;
	}

	public Integer getInvalidRecords() {
		return invalidRecords;
	}

	public void setInvalidRecords(Integer invalidRecords) {
		this.invalidRecords = invalidRecords;
	}

//	public UploadedFile getFile() {
//		return file;
//	}
//
//	public void setFile(UploadedFile file) {
//		this.file = file;
//		this.fileSize = file.getSize();
//	}

	public boolean isInvalid() {
		return invalid;
	}

	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public DateTime getDataImport() {
		return dataImport;
	}

	public void setDataImport(DateTime dataImport) {
		this.dataImport = dataImport;
	}
	
	

}
