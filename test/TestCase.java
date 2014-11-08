
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Agustinus Agri
 */
public class TestCase {
    
    public static void main(String[] args) {
        String todayAsString = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
        System.out.println(todayAsString);
    }
    
}
