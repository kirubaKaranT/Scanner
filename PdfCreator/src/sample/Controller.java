package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.sourceforge.tess4j.Tesseract;

import java.io.*;
import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class Controller  {

    @FXML
    private TextArea textarea;
    private String filename;
    private  String stringtoload;
    public void onButtonClicked()
    {
        System.out.println("hello guys"+textarea.getText());
    }
    public void open()throws Exception{

        FileChooser fileChooser=new FileChooser();
        fileChooser.setTitle("Open Source File ");
        FileChooser.ExtensionFilter extensionFilter=new FileChooser.ExtensionFilter("png(*.png)","*.PNG");
        FileChooser.ExtensionFilter extensionFilter1=new FileChooser.ExtensionFilter("jpg(*.jpg)","*.JPG");
        FileChooser.ExtensionFilter extensionFilter2=new FileChooser.ExtensionFilter("jpge(*.jpge)","*.JPGE");
        fileChooser.getExtensionFilters().addAll(extensionFilter,extensionFilter1,extensionFilter2);
        File openedfile=fileChooser.showOpenDialog(new Stage());

        if(openedfile.canRead()&&openedfile.canWrite()&&openedfile.getTotalSpace()>0) {
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"success");
        alert.show();
        stringtoload=openedfile.toString();
      filename=openedfile.getName();
      //System.out.println(filename);
            Tesseract tesseract=new Tesseract();
            String result=tesseract.doOCR(openedfile);

            textarea.clear();

        textarea.setText(result);
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR,"sorry you dont have permission to do");
        alert.show();
        }

    }

    public void saveas()throws Exception{
        FileChooser fileChooser=new FileChooser();
        FileChooser.ExtensionFilter extensionFilter=new FileChooser.ExtensionFilter("pdf(*.pdf)","*.pdf");
        FileChooser.ExtensionFilter extensionFilter1=new FileChooser.ExtensionFilter("TXT(*.txt)","*.txt");
        FileChooser.ExtensionFilter extensionFilter2=new FileChooser.ExtensionFilter("DOC(*.doc)","*.doc");
        fileChooser.getExtensionFilters().addAll(extensionFilter,extensionFilter1,extensionFilter2);
        fileChooser.setTitle("select the directory");
        File filetosave=fileChooser.showSaveDialog(new Stage());
        FileWriter fileWriter=new FileWriter(filetosave);

        fileWriter.flush();
        Scanner scanner=new Scanner(textarea.getText());
        while (scanner.hasNext()){
            String stringtoadd=scanner.nextLine();
            fileWriter.append(stringtoadd+"\n");

        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION,"success");
        alert.show();
        fileWriter.close();
    }
    public void exit() throws Exception{
        javafx.application.Platform.exit();

    }


}
