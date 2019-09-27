package bagel.project;


import java.awt.Toolkit;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

public class FXMLDocumentController {

    @FXML
    private TextField White_qty;

    @FXML
    private TextField Wheat_qty;

    @FXML
    private TextField Text_White;

    @FXML
    private TextField Text_Whole;
    
     @FXML
    private TextField TextRegCoffee;

    @FXML
    private TextField TextCappuccino;

    @FXML
    private TextField TextCafeAuLait;

    @FXML
    private CheckBox chkCreamCheese;

    @FXML
    private CheckBox chkButter;

    @FXML
    private CheckBox chkBlueberry;

    @FXML
    private CheckBox chkPeach;

    @FXML
    private CheckBox chkRaspberry;

    @FXML
    private RadioButton radNoCoffee;

    @FXML
    private ToggleGroup mygroup;

    @FXML
    private RadioButton radRegCoffee;

    @FXML
    private RadioButton radCappuccino;

    @FXML
    private RadioButton radCafeAuLait;

    @FXML
    private RadioButton radWhite;

    @FXML
    private ToggleGroup group;

    @FXML
    private RadioButton radWheat;

    @FXML
    private Button btnCalculate;

    @FXML
    private Button btnReset;

    @FXML
    private RadioButton radNoBagel;
    
    @FXML
    private ImageView img_Bagel;

    @FXML
    private ImageView img_Coffee;

    @FXML
    private Label lblSubtotal;

    @FXML
    private Label lblTax;

    @FXML
    private Label lblTotal;

    @FXML
    private Button btnPrint;

    @FXML
    private Button btnExit;
    
    @FXML
    void CalculateButtonHandler(ActionEvent event) throws IOException {
        String price = null;
        String price1 = null;
        double prc = 0;
        double prc1 = 0;
        double white = 0;
        double whole = 0; 
        double RegCoffee = 0;
        double Cappuccino = 0;
        double CafeAuLait = 0;
        if(radWhite.isSelected()){
        String tax1 = White_qty.getText();
        white = Double.parseDouble(tax1);
        prc = white*1.25;
        price = Double.toString(prc);
        Text_White.setText(price);
        }
        
        if(radWheat.isSelected()){
        String tax2 = Wheat_qty.getText();
        whole = Double.parseDouble(tax2);
        prc1 = whole*1.50;
        price1 = Double.toString(prc1);
        Text_Whole.setText(price1);
        }
            
            double value = prc + prc1;
            
            
            if (chkCreamCheese.isSelected()){
               value += (0.50);
            }
            if (chkButter.isSelected()){
               value += (0.25);
            }
            if (chkBlueberry.isSelected()){
               value += (0.75);
            }
            if (chkPeach.isSelected()){
               value += (0.75);
            }
            if (chkRaspberry.isSelected()){
               value += (0.75);
            }
            if(radNoCoffee.isSelected()){
                value += 0;
            }
            if (radRegCoffee.isSelected()){
                String Reg = TextRegCoffee.getText();
                RegCoffee = Double.parseDouble(Reg);                
               value += (1.25)*RegCoffee;
            }
            if (radCappuccino.isSelected()){
                String Cappucci = TextCappuccino.getText();
                Cappuccino= Double.parseDouble(Cappucci);  
               value  += (2.00)*Cappuccino;
            }
            if (radCafeAuLait.isSelected()){
                String Cafe = TextCafeAuLait.getText();
                CafeAuLait = Double.parseDouble(Cafe);  
               value += (1.75)*CafeAuLait;
            }
            if(!(radWhite.isSelected() | radWheat.isSelected())){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning!!!");
                alert.setHeaderText(null);
                alert.setContentText("Please Choose Any Bagel First");
                alert.showAndWait();
                
                lblSubtotal.setText("$0.00");
                lblTax.setText("$0.00");
                lblTotal.setText("$0.00");
                
            }
            
            String Rate = Double.toString(value);
            lblSubtotal.setText("$"+Rate);
            
            Double Taxes = Math.round(value*0.13*100.0)/100.0;
            String SalesTax = Double.toString(Taxes);
            lblTax.setText("$"+SalesTax);
            
            Double Total = Math.round((value + Taxes)*100.0)/100.0;
            String Amount = Double.toString(Total);
            lblTotal.setText("$"+Amount);
              
    try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(".\\src\\bagel\\project\\Receipt.txt"), "utf-8"))) {
                writer.write("*******************************\n");
                writer.write("**      Duplicate Receipt    **\n");
                writer.write("**            GST#           **\n");
                writer.write("\n");
                writer.write("     Sheridan Bagel House      \n");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd      HH:mm:ss\n");  
                LocalDateTime now = LocalDateTime.now();  
                writer.write(dtf.format(now));  
                writer.write("Item:         Qty         Price\n");
                writer.write("-------------------------------\n");
                if(radWhite.isSelected()){
                writer.write(white + "      White Bagel     $" + prc + " \n"); }
                if(radWheat.isSelected()){
                writer.write(whole + "   Whole Wheat Bage   $" + prc1 + "\n"); }
                writer.write("Toppings:\n");
                if (chkCreamCheese.isSelected()){
                writer.write("  Cream Cheese             $0.50\n");
                }
                if (chkButter.isSelected()){
                writer.write("  Butter                   $0.25\n");
                }
                if (chkBlueberry.isSelected()){
                writer.write("  Blueberry                $0.75\n");
                }
                if (chkPeach.isSelected()){
                writer.write("  Peach                    $0.75\n");
                }
                if (chkRaspberry.isSelected()){
                writer.write("  Raspberry                $0.75\n");
                }
                writer.write("Coffee:\n");
                if (radRegCoffee.isSelected()){
                writer.write(RegCoffee + "  Regular Coffee      $1.25\n");
                }
                if (radCappuccino.isSelected()){
                writer.write(Cappuccino + "  Cappuccino         $2.00\n");
                }
                if (radCafeAuLait.isSelected()){
                writer.write(CafeAuLait + "  Cafe Au Lait       $1.75\n");
                }
                writer.write("                        --------\n");
                writer.write("Sub Total:                $" + Rate + "\n");
                writer.write("Tax:  (13%)               $" + Taxes+ "\n");
                writer.write("Total:                    $" + Total+ "\n");
                writer.write("Paid:                     $"  +Total+ "\n");
                writer.write("Change Due                $00.00\n");
                writer.write("\n");
                writer.write("Host Order Id: 621-456-218681\n");
                writer.write("\n");
                writer.write("**Thank You For Your Order At**\n");
                writer.write("**    Sheridan Bagel Shop:   **\n");
              
        }
    }
  

    @FXML
    void ExitButtonHandler(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    void PrintButtonAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\jenip\\OneDrive\\Desktop\\sheridan\\Object Oriented Programing2 - Java\\Bagel Project\\src\\bagel\\project"));
        File selectedFile = fc.showOpenDialog(null);
        
        if(selectedFile != null) {
            selectedFile.getAbsolutePath();
        } else {
            System.out.println("File is not valid ");
        }
    }
    @FXML
    void ResetButtonHandler(ActionEvent event) {
        White_qty.setText(null);
        Wheat_qty.setText(null);
        Text_White.setText(null);
        Text_Whole.setText(null);
        TextRegCoffee.setText(null);
        TextCappuccino.setText(null);
        TextCafeAuLait.setText(null);      
        lblSubtotal.setText("$0.00");
        lblTax.setText("$0.00");
        lblTotal.setText("$0.00");
        chkCreamCheese.setSelected(false);
        chkButter.setSelected(false);
        chkBlueberry.setSelected(false);
        chkRaspberry.setSelected(false);
        chkPeach.setSelected(false);
        radNoBagel.setSelected(true);
        radWhite.setSelected(false);
        radWheat.setSelected(false);
        radNoCoffee.setSelected(true);
        radRegCoffee.setSelected(false);
        radCappuccino.setSelected(false);
        radCafeAuLait.setSelected(false);
    }
    @FXML
    void HandleAction(KeyEvent event) {
        KeyCode key = event.getCode();

		           	 if (key == KeyCode.ENTER)
		           	 {
		               Toolkit.getDefaultToolkit().beep();
		               String price = null;
        String price1 = null;
        double prc = 0;
        double prc1 = 0;
        double white = 0;
        double whole = 0; 
        if(radWhite.isSelected()){
        String tax1 = White_qty.getText();
        white = Double.parseDouble(tax1);
        prc = white*1.25;
        price = Double.toString(prc);
        Text_White.setText(price);
       
        }
        
        if(radWheat.isSelected()){
        String tax2 = Wheat_qty.getText();
        whole = Double.parseDouble(tax2);
        prc1 = whole*1.50;
        price1 = Double.toString(prc1);
        Text_Whole.setText(price1);
        }
            
            double value = prc + prc1;
            
            
            if (chkCreamCheese.isSelected()){
               value += (0.50);
            }
            if (chkButter.isSelected()){
               value += (0.25);
            }
            if (chkBlueberry.isSelected()){
               value += (0.75);
            }
            if (chkPeach.isSelected()){
               value += (0.75);
            }
            if (chkRaspberry.isSelected()){
               value += (0.75);
            }
            if(radNoCoffee.isSelected()){
                value += 0;
            }
            if (radRegCoffee.isSelected()){
                String Reg = TextRegCoffee.getText();
                Double RegCoffee = Double.parseDouble(Reg);                
               value += (1.25)*RegCoffee;
            }
            if (radCappuccino.isSelected()){
                String Cappucci = TextCappuccino.getText();
                Double Cappuccino= Double.parseDouble(Cappucci);  
               value  += (2.00)*Cappuccino;
            }
            if (radCafeAuLait.isSelected()){
                String Cafe = TextCafeAuLait.getText();
                Double CafeAuLait = Double.parseDouble(Cafe);  
               value += (1.75)*CafeAuLait;
            }
            if(!(radWhite.isSelected() | radWheat.isSelected())){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning!!!");
                alert.setHeaderText(null);
                alert.setContentText("Please Choose Any Bagel First");
                alert.showAndWait();
                
                lblSubtotal.setText("$0.00");
                lblTax.setText("$0.00");
                lblTotal.setText("$0.00");
                
            }
            
            String Rate = Double.toString(value);
            lblSubtotal.setText(Rate);
            
            Double Taxes = Math.round(value*0.13*100.0)/100.0;
            String SalesTax = Double.toString(Taxes);
            lblTax.setText(SalesTax);
            
            Double Total = Math.round((value + Taxes)*100.0)/100.0;
            String Amount = Double.toString(Total);
            lblTotal.setText(Amount);
			}
    }
}
