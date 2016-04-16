package ckiptest;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import tw.cheyingwu.ckip.CKIP;
import tw.cheyingwu.ckip.Term;
import tw.cheyingwu.ckip.WordSegmentationService;
 
public class CkipTest {
	 public static void main(String[] args) {
	        WordSegmentationService c; //宣告一個class變數c
	        ArrayList<String> inputList = new ArrayList<>(); //宣告動態陣列 存切詞的name
	        ArrayList<String> TagList = new ArrayList<>();   //宣告動態陣列 存切詞的詞性
	        String s = "在聽這場演講前，我還以為SEO是跟CEO或COO類似的某個職位，結果竟然是指搜尋引擎最佳化。記得在大一的管理數學課上，盧信銘老師在第一堂課上就曾提到搜尋引擎的ranking，不過那時候也只是很概略的講。現在才知道ranking對於一個網站的重要性，而SEO便是要想辦法讓網站的ranking能在越上面越好，所以SEO算是網路行銷重要的一環，能讓網站ranking往前，也就能讓網站的流量提高。不過在聽完前面的解說，以為SEO就單單只要把ranking往前，感覺這工作好像不是這麼複雜，但其實整個網站裡面有這麼多的元素，有這麼多的關鍵字，哪些是重要的，哪些是需要特別加強的，這些都是必須要好好分析的，甚至連網址的結構都必須好好規劃，而且要把SEO做好就必須先搞懂google ranking的機制，而這感覺也是不小的工程。身為一個常常使用google查詢的資管學生，我想我很能了解SEO的重要性，因為通常一般大眾在進行關鍵字搜尋時，並不會看非常多網站，可能也就前面幾個而已，但自然搜尋的排名在前面並不足夠，因為我們很常點進去找不到我們要的資訊就又跳出了，又或者在自然搜尋結果所提供的資訊不會想讓人點進去，而這些都會造成網站流量還是不如預期，因此除了在SEO上下功夫外，整個網站的設計也是很重要的。星期日去徵才博覽會參觀時，偶然發現一家網路公司專門在做關鍵字廣告與SEO，今天去那家公司參訪，更能體會到實務上SEO是怎麼與其他部門互動的。績效部門會與顧客溝通並給予網站設計與預算上的意見，用realtime的數據分析目前網站的情況，而實際進行SEO工作的就是優化部門，分成兩種工程師，一種是優化工程師，基本上主要的工作就是想辦法讓ranking往前，另一種則是資源工程師，也就是想辦法在相關的網站上建立連結到這個網站，優化部門也會與設計部的網頁設計師溝通，來讓整個網站對於使用者來說是好用的，且容易尋找到使用者所想要的資訊。聽完演講後又去參訪完公司，對於這個產業可以說是更深入的暸解了，在這資訊流通極為快速的時代，每天幾乎每個人都會使用搜尋引擎，對於一家公司來說，網站是一個很重要的行銷平台，也是一家公司是否能被別人看到的關鍵，而我以前以為網路行銷就只是單純把網站寫好，然後讓網站很user friendly就好了，但其實這樣並不夠，因為一個再好的網站沒有流量也等於是在養蚊子，而一個有高流量的網站也不一定代表就是一個好網站，因為還要看其跳出率是否在同行業中較高。每一種行業對於網路行銷的標準及願意投入的資金皆不一樣，但是大家的共通點就是，一個好的網站從被搜尋到到下訂單或者打電話詢價，這整個完整的過程要是流暢的，這也是網路行銷公司的任務所在。";
	         
	        System.out.println("********** 使用中研院斷詞伺服器 *********");
	         
	        c = new CKIP( "140.109.19.104" , 1501, "willy2721", "ckip0429"); //輸入申請的IP、port、帳號、密碼
	         
	        c.setRawText(s);
	        c.send(); //傳送至中研院斷詞系統服務使用
	         
	        for (Term t : c.getTerm()) {
	           
	            inputList.add(t.getTerm()); // t.getTerm()會讀到斷詞的String，將其存到inputList陣列
	            TagList.add(t.getTag());    // t.getTag() 會讀到斷詞的詞性，將其存到TagList陣列
	        }
	         
	        //將資料output成檔案
	        try {
	            FileWriter fr1 = new FileWriter("output.txt");
	            BufferedWriter bw = new BufferedWriter(fr1);
	            for(int i=0;i<inputList.size();i++)
	            {
	                bw.write(TagList.get(i)+"\t");
	                if(i != 0)
	                	bw.write("w[-1]=" + inputList.get(i-1) + " ");
	                
	                bw.write("w[0]=" + inputList.get(i));
	                
	                if(i != inputList.size()-1)
	                	bw.write(" w[1]=" + inputList.get(i+1));
	                
	                if(i != 0)
	                	bw.write(" pos[-1]=" + TagList.get(i-1) + " ");
	                
	                bw.write("pos[0]=" + TagList.get(i));
	                
	                if(i != inputList.size()-1)
	                	bw.write(" pos[1]=" + TagList.get(i+1));
	                
	                if(i == 0 || inputList.get(i-1).equals("。"))
	                	bw.write(" __BOS__");
	                else if (i == inputList.size()-1 || inputList.get(i).equals("。"))
	                	bw.write(" __EOS__"+'\n');
	                bw.newLine();
	            }
	            bw.close();
	             
	             
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	         
	    }
}
