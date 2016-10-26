package com.xwysun.account.OpenSrc;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.xwysun.account.Bean.Commodity;
import com.xwysun.account.Bean.SellCount;
import com.xwysun.account.Bean.SellRecords;
import com.xwysun.account.Bean.SimpleCount;
import com.xwysun.account.Utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Created by xwysu on 2016/8/8.
 */
public class SaveExcelHelper {
    private String savePath;
    private Context context;
    public ArrayList<SimpleCount> simpleCounts = new ArrayList<>();
    public ArrayList<SellCount> sellCounts=new ArrayList<>();
    private double sum;
    public SaveExcelHelper(Context c){
        context=c;
    }
    public void initPath(){
        Calendar calendar=Utils.getCalendarOfLastMonthStart();
        savePath=SDcardHelper.getSDCardBaseDir()+"/"+calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+".xls";
        Log.d(SaveExcelHelper.class.getSimpleName(),savePath);
    }

    public void setDataSrc(ArrayList<SimpleCount> simpleCounts,ArrayList<SellCount> sellCounts,double sum){
        this.simpleCounts=simpleCounts;
        this.sellCounts=sellCounts;
        this.sum=sum;
    }

    public void CreateExcel()  {
        initPath();
        int i=0;
        try{
            WritableWorkbook excel= Workbook.createWorkbook(new File(savePath));
            WritableSheet sheet=excel.createSheet("总表",0);
            sheet.addCell(new Label(0,0,"商品名称"));
            sheet.addCell(new Label(1,0,"商品单价"));
            sheet.addCell(new Label(2,0,"总销售量"));
            for ( i=0;i<sellCounts.size();i++){
                sheet.addCell(new Label(i+3,0,sellCounts.get(i).getUser().getUsername()+"销售量"));
            }
            for ( i=0;i<simpleCounts.size();i++){
                SimpleCount simplecount=simpleCounts.get(i);
                for (int j=0;j<sellCounts.size()+3;j++){
                    if (j == 0) {
                        sheet.addCell(new Label(j,i+1,simplecount.getCommodity().getName()));
                    }else if (j == 1){
                        sheet.addCell(new Label(j,i+1,Utils.DoubletoString(simplecount.getCommodity().getPrice())));
                    }else if (j == 2){
                        sheet.addCell(new Label(j,i+1,Utils.DoubletoString(simplecount.getCountSale())));
                    }else {
                        sheet.addCell(new Label(j,i+1,Utils.DoubletoString(sellCounts.get(j-3).getSellMap().get(simplecount.getCommodity()))));
                    }
                }
            }
            sheet.addCell(new Label(0,i+2,"总销售额"));
            sheet.addCell(new Label(1,i+2,Utils.DoubletoString(sum)));
            excel.write();
            excel.close();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            Utils.toast(context,"写入结束");
        }
    }
}
