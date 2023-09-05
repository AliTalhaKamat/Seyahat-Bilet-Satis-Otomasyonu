package com.example.alitalhakamat211030029;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ComboBox<String> cmbKalkis;

    @FXML
    private ComboBox<String> cmbOtobus;

    @FXML
    private ComboBox<String> cmbVaris;

    @FXML
    private ComboBox<String> cmbYolcuSecimi;

    @FXML
    private DatePicker dtpSeyahatTarihi;

    @FXML
    private Spinner<Integer> spnAdet;

    @FXML
    private TextField txtAdSoyad;

    @FXML
    private TextField txtAdet;

    @FXML
    private TextField txtBiletGoster;

    @FXML
    private TextField txtBiletNo;

    @FXML
    private TextField txtKalkis;

    @FXML
    private TextField txtOtobus;

    @FXML
    private TextField txtSeyahatTarihi;

    @FXML
    private TextField txtVaris;


    private ArrayList<Seyahat> seyahatler=new ArrayList<Seyahat>();




    @FXML
    void seyahatKayit(ActionEvent event) {
        String AdSoyad=txtAdSoyad.getText();
        String kalkis=cmbKalkis.getValue();
        String varis=cmbVaris.getValue();
        LocalDate seyahatTarihi=dtpSeyahatTarihi.getValue();
        String otobus=cmbOtobus.getValue();
        int biletAdet=spnAdet.getValue();


        if (txtBiletNo.getText().isEmpty() || txtAdSoyad.getText().isEmpty()){
            Alert seyahatmesaji=new Alert(Alert.AlertType.ERROR);
            seyahatmesaji.setTitle("Hata!");
            seyahatmesaji.setHeaderText("Bilet Numarası veya Ad Soyad girilmedi...");
            seyahatmesaji.show();
            return;

        }

        int biletNo=Integer.parseInt(txtBiletNo.getText());


        for (int i = 0; i < seyahatler.size(); i++) {
            if (biletNo == seyahatler.get(i).getBiletNo()){
                Alert seyahetmesaji=new Alert(Alert.AlertType.ERROR);
                seyahetmesaji.setTitle("Hata!");
                seyahetmesaji.setHeaderText(seyahatler.get(i).getBiletNo()+" bilet numaralı kişi zaten kayıtlı, lütfen başka bir bilet numarası yazınız...");
                seyahetmesaji.show();
                return;

            }
        }




        Seyahat seyahat=new Seyahat(AdSoyad, kalkis, varis, otobus, seyahatTarihi, biletNo, biletAdet);
        seyahatler.add(seyahat);

        try {
            FileOutputStream fos=new FileOutputStream("seyahatbilgileri.dat");
            ObjectOutputStream oss=new ObjectOutputStream(fos);
            oss.writeObject(seyahatler);
            fos.close();
            oss.close();
            Alert bilgilendirmemesaji=new Alert(Alert.AlertType.INFORMATION);
            bilgilendirmemesaji.setTitle("Bilgi");
            bilgilendirmemesaji.setHeaderText(seyahat.getAdSoyad()+" kişisi kaydedildi...");
            bilgilendirmemesaji.show();

            cmbYolcuSecimi.getItems().add(seyahat.getAdSoyad());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void seyahatSil(ActionEvent event) {

        int secilen=cmbYolcuSecimi.getSelectionModel().getSelectedIndex();



        if (secilen < 0) {

            Alert bilgilendirme=new Alert(Alert.AlertType.ERROR);
            bilgilendirme.setTitle("Hata!");
            bilgilendirme.setHeaderText("Silinecek kayıt bulunamadı...");
            bilgilendirme.show();
            return;
        }

        String adsoyad=seyahatler.get(secilen).getAdSoyad();

        cmbYolcuSecimi.getItems().remove(secilen);
        txtBiletGoster.clear();
        txtVaris.clear();
        txtKalkis.clear();
        txtOtobus.clear();
        txtAdet.clear();
        txtSeyahatTarihi.clear();

        seyahatler.remove(secilen);

        try {
            FileOutputStream fos=new FileOutputStream("seyahatbilgileri.dat");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(seyahatler);
            fos.close();
            oos.close();

            Alert bilgilendirmemesaji=new Alert(Alert.AlertType.INFORMATION);
            bilgilendirmemesaji.setTitle("Bilgi");
            bilgilendirmemesaji.setHeaderText(adsoyad + " kişisi silindi...");
            bilgilendirmemesaji.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void yolcuGoster(ActionEvent event) {

        int secilen=cmbYolcuSecimi.getSelectionModel().getSelectedIndex();

        if (secilen < 0) {
            return;
        }


        txtBiletGoster.setText(String.valueOf(seyahatler.get(secilen).getBiletNo()));
        txtKalkis.setText(seyahatler.get(secilen).getKalkis());
        txtVaris.setText(seyahatler.get(secilen).getVaris());
        txtSeyahatTarihi.setText(String.valueOf(seyahatler.get(secilen).getseyahatTarihi()));
        txtOtobus.setText(seyahatler.get(secilen).getOtobus());
        txtAdet.setText(String.valueOf(seyahatler.get(secilen).getBiletAdet()));

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cmbKalkis.setItems(FXCollections.observableArrayList("İstanbul","Ankara","Giresun","Adıyaman","Çanakkale",
                "Sinop","Tokat","Adana","Bursa","İzmir","Denizli","Gümüşhane","Trabzon"));

        cmbVaris.setItems(FXCollections.observableArrayList("İstanbul","Ankara","Giresun","Adıyaman","Çanakkale",
                "Sinop","Tokat","Adana","Bursa","İzmir","Denizli","Gümüşhane","Trabzon"));

        cmbOtobus.setItems(FXCollections.observableArrayList("Tourismo","Travego","Setra",
                "Man","Neoplan","Maz","Mercedes","Temsa","Isuzu","Otokar","Iveco"));

        spnAdet.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,50, 1,1));

        try {
            FileInputStream fis=new FileInputStream("seyahatbilgileri.dat");
            ObjectInputStream ois=new ObjectInputStream(fis);
            seyahatler= (ArrayList<Seyahat>) ois.readObject();
            ois.close();

            for (int i = 0; i < seyahatler.size(); i++) {
                cmbYolcuSecimi.getItems().add(seyahatler.get(i).getAdSoyad());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
