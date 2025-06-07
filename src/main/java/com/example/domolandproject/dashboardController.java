package com.example.domolandproject;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class dashboardController implements Initializable {
    @FXML
    private Button pawButton;
    @FXML
    private Label username;
    @FXML
    private Button coach_btn;
    @FXML
    private Button dashboard_btn;
    @FXML
    private Button members_btn;
    @FXML
    private Button payment_btn;
    @FXML
    private Button closeBlack;
    @FXML
    private Button closeRed;
    @FXML
    private Button coach_addBtn;

    @FXML
    private TextField coach_address;

    @FXML
    private TextField coach_coachID;

    @FXML
    private TableColumn<coachData, String> coach_col_address;

    @FXML
    private TableColumn<coachData, String> coach_col_coachID;

    @FXML
    private TableColumn<coachData, String> coach_col_gender;

    @FXML
    private TableColumn<coachData, String> coach_col_name;

    @FXML
    private TableColumn<coachData, String> coach_col_phoneNum;

    @FXML
    private TableColumn<coachData, String> coach_col_status;

    @FXML
    private Button coach_deleteBtn;

    @FXML
    private AnchorPane coach_form;

    @FXML
    private ComboBox<String> coach_gender;

    @FXML
    private TextField coach_name;

    @FXML
    private TextField coach_phoneNum;

    @FXML
    private Button coach_resetBtn;

    @FXML
    private ComboBox<String> coach_status;

    @FXML
    private TableView<coachData> coach_tableView;

    @FXML
    private Button coach_updateBtn;

    @FXML
    private Label dashboard_NC;

    @FXML
    private Label dashboard_NM;

    @FXML
    private Label dashboard_TI;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AreaChart<?, ?> dashboard_incomeChart;

    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button members_addbtn;

    @FXML
    private TextField members_address;

    @FXML
    private Button members_clearBtn;

    @FXML
    private TableColumn<memberData, String> members_col_address;

    @FXML
    private TableColumn<memberData, String> members_col_customerID;

    @FXML
    private TableColumn<memberData, String> members_col_endDate;

    @FXML
    private TableColumn<memberData, String> members_col_gender;

    @FXML
    private TableColumn<memberData, String> members_col_name;

    @FXML
    private TableColumn<memberData, String> members_col_phoneNum;

    @FXML
    private TableColumn<memberData, String> members_col_schedule;

    @FXML
    private TableColumn<memberData, String> members_col_startDate;

    @FXML
    private TableColumn<memberData, String> members_col_status;

    @FXML
    private TextField members_customerID;

    @FXML
    private Button members_deleteBtn;

    @FXML
    private DatePicker members_endDate;

    @FXML
    private AnchorPane members_form;

    @FXML
    private ComboBox<String> members_gender;

    @FXML
    private TextField members_name;

    @FXML
    private TextField members_phoneNum;

    @FXML
    private ComboBox<String> members_schedule;

    @FXML
    private DatePicker members_startDate;

    @FXML
    private ComboBox<String> members_status;

    @FXML
    private TableView<memberData> members_tableView;

    @FXML
    private Button members_updateBtn;

    @FXML
    private Button minimize;

    @FXML
    private TextField payment_amount;

    @FXML
    private Label payment_change;

    @FXML
    private TableColumn<memberData, String> payment_col_customerID;

    @FXML
    private TableColumn<memberData, String> payment_col_endDate;

    @FXML
    private TableColumn<memberData, String> payment_col_name;

    @FXML
    private TableColumn<memberData, String> payment_col_startDate;

    @FXML
    private TableColumn<memberData, String> payment_col_status;

    @FXML
    private ComboBox<String> payment_customerID;

    @FXML
    private AnchorPane payment_form;

    @FXML
    private ComboBox<String> payment_name;

    @FXML
    private Button payment_payBtn;

    @FXML
    private TableView<memberData> payment_tableView;

    @FXML
    private Label payment_total;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    public void emptyFields(){
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error Message");
//        alert.setHeaderText(null);
//        alert.setContentText("Please fill in all the blank fields");
//        alert.showAndWait();
        PopupUtil.showCustomPopup("Please fill in all the blank fields", "OK","file:C:/Users/eugen/Documents/Java/DomolandProject/Images/angelPom.png");

    }
    public void dashboardNM(){
        String sql = "SELECT COUNT(id) FROM member WHERE status = 'Paid'";

        connect = DataBase.connectDb();

        int nm = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                nm = result.getInt("COUNT(id)");
            }

            dashboard_NM.setText(String.valueOf(nm));
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void dashboardNC(){
        String sql = "SELECT COUNT(id) FROM coach WHERE status = 'Active'";

        connect = DataBase.connectDb();

        int tc = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                tc = result.getInt("COUNT(id)");
            }

            dashboard_NC.setText(String.valueOf(tc));
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void dashboardTI(){
        String sql = "SELECT SUM(price) FROM member WHERE status = 'Paid'";

        connect = DataBase.connectDb();

        double ti = 0;
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                ti = result.getInt("SUM(price)");
            }

            dashboard_TI.setText("$" + String.valueOf(ti));
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void dashboardChart(){
        dashboard_incomeChart.getData().clear();
        String sql = "SELECT startDate, SUM(price) FROM member WHERE status = 'Paid' GROUP BY startDate ORDER BY TIMESTAMP(startDate) ASC LIMIT 10";

        connect = DataBase.connectDb();

        XYChart.Series chart = new XYChart.Series();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){
                chart.getData().add(new XYChart.Data(result.getString(1), result.getDouble(2)));
            }

            dashboard_incomeChart.getData().add(chart);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void coachesAddBtn(){
        String sql = "INSERT INTO coach (coachId, name, address, gender, phoneNum, status) " + "VALUES(?,?,?,?,?,?)";

        connect = DataBase.connectDb();

        try{

            Alert alert;
            if (coach_coachID.getText().isEmpty() || coach_name.getText().isEmpty()
                || coach_address.getText().isEmpty() || coach_gender.getSelectionModel().getSelectedItem() == null
                || coach_phoneNum.getText().isEmpty() || coach_status.getSelectionModel().getSelectedItem() == null){
                emptyFields();
            }else{
                String checkData = "SELECT coachId FROM coach WHERE coachId = '" + coach_coachID.getText() + "'";
                statement  = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()){
//                    alert  = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Coach ID: " + coach_coachID.getText() + " was already taken!");
//                    alert.showAndWait();

                    PopupUtil.showCustomPopup("Coach ID: " + coach_coachID.getText() + " was already taken!", "OK","file:C:/Users/eugen/Documents/Java/DomolandProject/Images/angelPom.png");


                }else{
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, coach_coachID.getText());
                    prepare.setString(2, coach_name.getText());
                    prepare.setString(3, coach_address.getText());
                    prepare.setString(4, (String)coach_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, coach_phoneNum.getText());
                    prepare.setString(6, (String)coach_status.getSelectionModel().getSelectedItem());

//                    alert  = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successfully Added");
//                    alert.showAndWait();

                    PopupUtil.showCustomPopup("Successfully Added", "OK","file:C:/Users/eugen/Documents/Java/DomolandProject/Images/shoppingPom.png");

                    // To Insert all Data
                    prepare.executeUpdate();
                    // To update tableView
                    coachesShowData();
                    // To clear all fields
                    coachesClearBtn();
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void coachesUpdateBtn(){
        String sql = "UPDATE coach SET name = '" + coach_name.getText()
                + "',  address = '" + coach_address.getText()
                + "', gender = '" + coach_gender.getSelectionModel().getSelectedItem()
                + "',  phoneNum = '" + coach_phoneNum.getText()
                + "', status = '" + coach_status.getSelectionModel().getSelectedItem()
                + "' WHERE coachId = '" + coach_coachID.getText()
                + "'";
        connect = DataBase.connectDb();

        try{
            Alert alert;
            if (coach_coachID.getText().isEmpty() || coach_name.getText().isEmpty()
                    || coach_address.getText().isEmpty() || coach_gender.getSelectionModel().getSelectedItem() == null
                    || coach_phoneNum.getText().isEmpty() || coach_status.getSelectionModel().getSelectedItem() == null){
                emptyFields();
            }else {
                alert  = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update Coach ID: " + coach_coachID.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    alert  = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated");
                    alert.showAndWait();

                    // To update tableView
                    coachesShowData();
                    // To clear all fields
                    coachesClearBtn();
                }else{
                    alert  = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Update cancelled");
                    alert.showAndWait();
                }


            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void coachesDeleteBtn(){
        String sql = "DELETE FROM coach WHERE coachId = '"+ coach_coachID.getText() + "'";
        connect = DataBase.connectDb();

        try{
            Alert alert;
            if (coach_coachID.getText().isEmpty() || coach_name.getText().isEmpty()
                    || coach_address.getText().isEmpty() || coach_gender.getSelectionModel().getSelectedItem() == null
                    || coach_phoneNum.getText().isEmpty() || coach_status.getSelectionModel().getSelectedItem() == null){
                emptyFields();
            }else {
                alert  = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Coach ID: " + coach_coachID.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){
                    prepare = connect.prepareStatement(sql);
                    // To execute the query you typed
                    prepare.executeUpdate();

                    alert  = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted");
                    alert.showAndWait();

                    // To update tableView
                    coachesShowData();
                    // To clear all fields
                    coachesClearBtn();
                }else{
                    alert  = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Delete cancelled");
                    alert.showAndWait();
                }


            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void coachesClearBtn(){
        coach_coachID.setText("");
        coach_name.setText("");
        coach_address.setText("");
        coach_gender.getSelectionModel().clearSelection();
        coach_phoneNum.setText("");
        coach_status.getSelectionModel().clearSelection();


    }
    public ObservableList<coachData> coachesDataList(){

        ObservableList<coachData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM coach";
        connect = DataBase.connectDb();
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            coachData cd;

            while(result.next()){
                cd = new coachData(result.getInt("id"),
                        result.getString("coachId"),
                        result.getString("name"),
                        result.getString("address"),
                        result.getString("gender"),
                        result.getInt("phoneNum"),
                        result.getString("status"));

                listData.add(cd);
            }

        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return listData;
    }

    private ObservableList<coachData> coachesListData;
    public void coachesShowData(){
        coachesListData = coachesDataList();

        coach_col_coachID.setCellValueFactory(new PropertyValueFactory<>("coachId"));
        coach_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        coach_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        coach_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        coach_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        coach_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        coach_tableView.setItems(coachesListData);
    }

    public void coachesSelect(){
        coachData cd = coach_tableView.getSelectionModel().getSelectedItem();
        int num = coach_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) return;

        coach_coachID.setText(cd.getCoachId());
        coach_name.setText(cd.getName());
        coach_address.setText(cd.getAddress());
        coach_gender.setValue(cd.getGender());
        coach_phoneNum.setText(String.valueOf(cd.getPhoneNum()));
        coach_status.setValue(cd.getStatus());

    }
    private double x = 0;
    private double y = 0;
    private final String[] gender = {"Male","Female","Others"};
    private final String[] status = {"Active","Inactive"};
    public void coachGenderList(){
        List<String> genderList = new ArrayList<>();
//        for (String data: gender){
//            genderList.add(data);
//        }
        genderList.addAll(Arrays.asList(gender));

        ObservableList listData = FXCollections.observableArrayList(genderList);
        coach_gender.setItems(listData);
    }
    public void coachStatusList(){
        List<String> statusList = new ArrayList<>();

        statusList.addAll(Arrays.asList(status));

        ObservableList listData = FXCollections.observableArrayList(statusList);
        coach_status.setItems(listData);
    }
    private int totalDay;
    private double price;
    public void membersAddBtn(){
        String sql = "INSERT INTO member (memberId, name, address, phoneNum, gender, schedule, startDate, endDate, price, status) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        connect = DataBase.connectDb();

        try{

            Alert alert;
            if (members_customerID.getText().isEmpty() || members_name.getText().isEmpty()
                    || members_phoneNum.getText().isEmpty() || members_address.getText().isEmpty()
                    || members_gender.getSelectionModel().getSelectedItem() == null || members_schedule.getSelectionModel().getSelectedItem() == null
                    || members_startDate.getValue() == null || members_endDate.getValue() == null){
                emptyFields();
            }else{
                String checkData = "SELECT memberId FROM member WHERE memberId = '" + members_customerID.getText() + "'";
                statement  = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()){
//                    alert  = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Member ID: " + members_customerID.getText() + " was already taken!");
//                    alert.showAndWait();

                    PopupUtil.showCustomPopup("Member ID: " + members_customerID.getText() + " was already taken!", "OK","file:C:/Users/eugen/Documents/Java/DomolandProject/Images/angelPom.png");


                }else{
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, members_customerID.getText());
                    prepare.setString(2, members_name.getText());
                    prepare.setString(3, members_address.getText());
                    prepare.setString(4, members_phoneNum.getText());
                    prepare.setString(5, (String) members_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(6, (String) members_schedule.getSelectionModel().getSelectedItem());
                    prepare.setString(7, String.valueOf(members_startDate.getValue()));
                    prepare.setString(8, String.valueOf(members_endDate.getValue()));

                    totalDay = (int) ChronoUnit.DAYS.between(members_startDate.getValue(), members_endDate.getValue());

                    price = totalDay * 50;

                    prepare.setString(9, String.valueOf(price));
                    prepare.setString(10, (String) members_status.getSelectionModel().getSelectedItem());

//                    alert  = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successfully Added");
//                    alert.showAndWait();

                    PopupUtil.showCustomPopup("Successfully Added", "OK","file:C:/Users/eugen/Documents/Java/DomolandProject/Images/shoppingPom.png");


                    // To Insert all Data
                    prepare.executeUpdate();
                    // To update tableView
                    membersShowData();
                    // To clear all fields
                    membersClearBtn();
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void membersUpdateBtn(){
        String sql = "UPDATE member SET name = '" + members_name.getText()
                + "', address = '" + members_address.getText()
                + "', phoneNum = '" + members_phoneNum.getText()
                + "', gender = '" + members_gender.getSelectionModel().getSelectedItem()
                + "', schedule = '" + members_schedule.getSelectionModel().getSelectedItem()
                + "', startDate = '" + members_startDate.getValue()
                + "', endDate = '" + members_endDate.getValue()
                + "', price = '" + String.valueOf(price)
                + "', status = '" + members_status.getSelectionModel().getSelectedItem()
                + "' WHERE memberId = '" + members_customerID.getText()
                + "'";
        connect = DataBase.connectDb();

        try{
            Alert alert;
            if (members_customerID.getText().isEmpty() || members_name.getText().isEmpty()
                    || members_phoneNum.getText().isEmpty() || members_address.getText().isEmpty()
                    || members_gender.getSelectionModel().getSelectedItem() == null || members_schedule.getSelectionModel().getSelectedItem() == null
                    || members_startDate.getValue() == null || members_endDate.getValue() == null){
                emptyFields();
            }else {
                alert  = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update Member ID: " + members_customerID.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    alert  = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated");
                    alert.showAndWait();

                    // To update tableView
                    membersShowData();
                    // To clear all fields
                    membersClearBtn();
                }else{
                    alert  = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Update cancelled");
                    alert.showAndWait();
                }


            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void membersDeleteBtn(){
        String sql = "DELETE FROM member WHERE memberId = '"+ members_customerID.getText() + "'";
        connect = DataBase.connectDb();

        try{
            Alert alert;
            if (members_customerID.getText().isEmpty() || members_name.getText().isEmpty()
                    || members_phoneNum.getText().isEmpty() || members_address.getText().isEmpty()
                    || members_gender.getSelectionModel().getSelectedItem() == null || members_schedule.getSelectionModel().getSelectedItem() == null
                    || members_startDate.getValue() == null || members_endDate.getValue() == null){
                emptyFields();
            }else {
                alert  = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete Member ID: " + members_customerID.getText() + " ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){
                    prepare = connect.prepareStatement(sql);
                    // To execute the query you typed
                    prepare.executeUpdate();

                    alert  = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted");
                    alert.showAndWait();

                    // To update tableView
                    membersShowData();
                    // To clear all fields
                    membersClearBtn();
                }else{
                    alert  = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Delete cancelled");
                    alert.showAndWait();
                }


            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void membersClearBtn(){
        members_customerID.setText("");
        members_name.setText("");
        members_address.setText("");
        members_phoneNum.setText("");
        members_gender.getSelectionModel().clearSelection();
        members_schedule.getSelectionModel().clearSelection();
        members_startDate.setValue(null);
        members_endDate.setValue(null);
        members_status.getSelectionModel().clearSelection();

    }
    public void membersGender(){
        List<String> genderList = new ArrayList<>();

        for (String data: gender){
            genderList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(genderList);
        members_gender.setItems(listData);

    }
    private String[] scheduleList = {"9am - 11am", "11am - 1pm", "1pm - 3pm", "3pm - 5pm","5pm - 7pm"};
    public void membersSchedule(){
        List<String> sl = new ArrayList<>();

        for (String data: scheduleList){
            sl.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(sl);
        members_schedule.setItems(listData);
    }

    private String memberStatus[] = {"Paid", "Unpaid"};

    public void membersStatus(){
        List<String> ms = new ArrayList<>();

        for (String data: memberStatus){
            ms.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(ms);
        members_status.setItems(listData);

    }
    public ObservableList<memberData> membersDataList(){
        ObservableList<memberData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM member";

        connect = DataBase.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            memberData md;

            while (result.next()){
                md = new memberData(result.getInt("id"),
                        result.getString("memberId"),
                        result.getString("name"),
                        result.getString("address"),
                        result.getInt("phoneNum"),
                        result.getString("gender"),
                        result.getString("schedule"),
                        result.getDate("startDate"),
                        result.getDate("endDate"),
                        result.getDouble("price"),
                        result.getString("status"));

                listData.add(md);
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listData;
    }
    private ObservableList<memberData> membersListData;
    public void membersShowData(){
        membersListData = membersDataList();

        members_col_customerID.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        members_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        members_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        members_col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        members_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        members_col_schedule.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        members_col_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        members_col_endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        members_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        members_tableView.setItems(membersListData);
    }
    public void membersSelect(){
        memberData md = members_tableView.getSelectionModel().getSelectedItem();
        int num = members_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) return;

        members_customerID.setText(md.getMemberId());
        members_name.setText(md.getName());
        members_address.setText(md.getAddress());
        members_gender.setValue(md.getGender());
        members_schedule.setValue(md.getSchedule());
        members_startDate.setValue(LocalDate.parse(String.valueOf(md.getStartDate())));
        members_endDate.setValue(LocalDate.parse(String.valueOf(md.getEndDate())));
        members_phoneNum.setText(String.valueOf(md.getPhoneNum()));
        members_status.setValue(md.getStatus());

    }
    private boolean isUpdating = false;
    public void paymentMemberId(){
        if (isUpdating) return;
        isUpdating = true;
        String sql = "SELECT memberId FROM member WHERE status = 'Unpaid'";
        connect = DataBase.connectDb();

        try{
            ObservableList<String> listData = FXCollections.observableArrayList();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                listData.add(result.getString("memberId"));
            }
            payment_customerID.setItems(listData);
            paymentName();
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            isUpdating = false;
        }
    }

    public void paymentName(){
        String sql = "SELECT name FROM member WHERE memberId = '" + payment_customerID.getSelectionModel().getSelectedItem() + "'";
        connect = DataBase.connectDb();

        try{
            ObservableList listData = FXCollections.observableArrayList();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                listData.add(result.getString("name"));
            }
            payment_name.setItems(listData);

            paymentDisplayTotal();

        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private double totalP;
    public void displayTotal(){

        String sql = "SELECT price FROM member WHERE name = '"
                + payment_name.getSelectionModel().getSelectedItem()
                + "' and memberId = '"
                + payment_customerID.getSelectionModel().getSelectedItem()
                + "'";

        connect = DataBase.connectDb();
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();


            if (result.next()){
                totalP = result.getDouble("price");
            }


        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void paymentDisplayTotal(){
        displayTotal();
        payment_total.setText("$"+ totalP);
    }
    private double amount;
    private double change;
    public void paymentAmount(){
        displayTotal();

        Alert alert;

        if (payment_amount.getText().isEmpty() || totalP == 0){
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Invalid :3");
//            alert.showAndWait();

            PopupUtil.showCustomPopup("Invalid", "Try Again","file:C:/Users/eugen/Documents/Java/DomolandProject/Images/shoppingPom.png");

            payment_amount.setText("");
        }else{
            amount = Double.parseDouble(payment_amount.getText());

            if (amount >= totalP){
                change = (amount - totalP);
                payment_change.setText("$" + String.valueOf(change));
            }else{
                payment_amount.setText("");
                change = 0;
                amount = 0;
            }

        }
    }
    public void paymentPayBtn(){

        String sql = "UPDATE member SET status = 'Paid' WHERE memberId = '"
                + payment_customerID.getSelectionModel().getSelectedItem() + "'";

        connect = DataBase.connectDb();

        try{
            Alert alert;
            if (totalP == 0 || change == 0 || payment_amount.getText().isEmpty()){
//                alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Something went wrong :3");
//                alert.showAndWait();

                PopupUtil.showCustomPopup("Something went wrong", "OK","file:C:/Users/eugen/Documents/Java/DomolandProject/Images/shoppingPom.png");

            }else{
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful");
                    alert.showAndWait();

                    paymentShowData();
                    paymentClear();
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled");
                    alert.showAndWait();
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void paymentClear(){
        payment_customerID.getSelectionModel().clearSelection();
        payment_name.getSelectionModel().clearSelection();
        payment_total.setText("$0.0");
        payment_amount.setText("");
        payment_change.setText("$0.0");

        amount = 0;
        change = 0;
        totalP = 0;
    }
    public ObservableList<memberData> paymentDataList(){
        ObservableList<memberData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM member";

        connect = DataBase.connectDb();

        try{
            memberData md;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                md = new memberData(result.getInt("id"),
                        result.getString("memberId"),
                        result.getString("name"),
                        result.getString("address"),
                        result.getInt("phoneNum"),
                        result.getString("gender"),
                        result.getString("schedule"),
                        result.getDate("startDate"),
                        result.getDate("endDate"),
                        result.getDouble("price"),
                        result.getString("status"));

                listData.add(md);
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listData;
    }
    private ObservableList<memberData> paymentListData;

    public void paymentShowData(){
        paymentListData = paymentDataList();

        payment_col_customerID.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        payment_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        payment_col_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        payment_col_endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        payment_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        payment_tableView.setItems(paymentListData);
    }
    public void displayUsername(){

        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);

        username.setText(user);

    }
    public void switchForm(ActionEvent event){

        // Remove 'selected' style from all buttons
        dashboard_btn.getStyleClass().remove("selected");
        coach_btn.getStyleClass().remove("selected");
        members_btn.getStyleClass().remove("selected");
        payment_btn.getStyleClass().remove("selected");

        if (event.getSource() == dashboard_btn){

            dashboard_form.setVisible(true);
            coach_form.setVisible(false);
            members_form.setVisible(false);
            payment_form.setVisible(false);

            dashboardChart();
            dashboardNM();
            dashboardNC();
            dashboardTI();

            // Add 'selected' style to the clicked button
            dashboard_btn.getStyleClass().add("selected");

        }else if (event.getSource() == coach_btn){

            dashboard_form.setVisible(false);
            coach_form.setVisible(true);
            members_form.setVisible(false);
            payment_form.setVisible(false);

            coachGenderList();
            coachStatusList();
            coachesShowData();

            // Add 'selected' style to the clicked button
            coach_btn.getStyleClass().add("selected");

        }else if (event.getSource() == members_btn){

            dashboard_form.setVisible(false);
            coach_form.setVisible(false);
            members_form.setVisible(true);
            payment_form.setVisible(false);

            membersShowData();
            membersGender();
            membersSchedule();
            membersStatus();

            // Add 'selected' style to the clicked button
            members_btn.getStyleClass().add("selected");

        }else if (event.getSource() == payment_btn){

            dashboard_form.setVisible(false);
            coach_form.setVisible(false);
            members_form.setVisible(false);
            payment_form.setVisible(true);

            paymentShowData();
            paymentMemberId();
            paymentName();

            // Add 'selected' style to the clicked button
            payment_btn.getStyleClass().add("selected");

        }
    }
    public void logout(){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout? ");

            Optional<ButtonType> option =  alert.showAndWait();

            if (option.get().equals(ButtonType.OK)){
                loadLoginPage();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void minimize(){

        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    public void close(){
        javafx.application.Platform.exit();
    }
    public void pawBtn(){
        PopupUtil.showCustomPopup("Congrats! You are officially part of the Pompompurin secret cult!", "Been waiting for this all my life!","file:C:/Users/eugen/Documents/Java/DomolandProject/Images/pom-and-friends.png");

    }
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboard_btn.getStyleClass().add("selected");

        displayUsername();

        dashboardChart();
        dashboardNM();
        dashboardNC();
        dashboardTI();

        coachGenderList();
        coachStatusList();
        coachesShowData();

        membersGender();
        membersSchedule();
        membersStatus();
        membersShowData();

        paymentMemberId();
        paymentShowData();

        closeRed.setVisible(false);
        closeBlack.setVisible(true);

        closeRed.setOnMouseEntered((MouseEvent event) -> {
            closeRed.setVisible(true);
        });
        closeRed.setOnMouseExited((MouseEvent event) -> {
            closeRed.setVisible(false);
        });
        closeBlack.setOnMouseEntered((MouseEvent event) -> {
            closeRed.setVisible(true);
        });
        closeBlack.setOnMouseExited((MouseEvent event) -> {
            closeRed.setVisible(false);
        });
    }
    private void loadLoginPage(){
        // Link your login form
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(dashboardController.class.getResource("login.fxml")));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.initStyle(StageStyle.TRANSPARENT);

            root.setOnMousePressed((MouseEvent event) ->{
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
                stage.setOpacity(1);
            });

            root.setOnMouseReleased((MouseEvent event) -> {
                stage.setOpacity(1);
            });
            Image icon = new Image("file:C:/Users/eugen/Documents/Java/DomolandProject/Images/app-icon.png");

            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();

            logout_btn.getScene().getWindow().hide();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
