package project_view;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.TransferHandler;

import project_model.Graph;

/**
 * Xử lý việc lấy thông tin của file vừa thả vào giao diện.
 * @author Tu
 *
 */
public class FileDropHandler extends TransferHandler {
	private FileDrop fileDrop;
	
	public FileDropHandler(FileDrop fileDrop) {
		this.fileDrop = fileDrop;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean canImport(TransferHandler.TransferSupport support) {
		if (!support.isDrop()) {
			return false;
		}

		if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
			return false;
		}

		return true;
	}

	@Override
	/**
	 * Phương thức sẽ chạy khi chúng ta mếm 1 file vào phần mềm.
	 */
	public boolean importData(TransferHandler.TransferSupport support) {
		if (!canImport(support)) {
			return false;
		}

		Transferable transferable = support.getTransferable();

		try {
			List<File> files = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
			if (files != null && !files.isEmpty()) {
				File file = files.get(0);
				String pathFile = file.getAbsolutePath();
				fileDrop.getGraph().loadGraph(pathFile);
				fileDrop.getPanelGraph().createVertex();
				fileDrop.setPanelGraph();
				return true;
			}
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public String readFile(String ulr) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(ulr));
		String re = "<html>";
		String line = "";
		while(true) {
			line = reader.readLine();
			if(line == null) {
				break;
			}
			
			re += line + "<br>";
		}
		re += "</html>";
		
		reader.close();
		return re;
	}
}
