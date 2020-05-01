package scrapper_anime;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class MainThread extends Thread{
///// Send to data base            		
	private static java.sql.Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private static String url = "jdbc:mysql://localhost:3306/gogoanime";
	private static String user = "root";
	private static String password = "";
	String EpisodeUrl;
	String AnimeUID;
	int i;
	String Name;

	public MainThread(String EpisodeUrl , String AnimeUID , int i , String Name) {
		this.EpisodeUrl = EpisodeUrl;
		this.AnimeUID = AnimeUID;
		this.i = i;
		this.Name= Name;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		//-- Extract Episode Page Html
            		
					try{

					
		Document EpisodePage = Jsoup.connect(EpisodeUrl).get();
		Elements MainElemetnss = EpisodePage.select("body");
		Elements ServerElements = EpisodePage.select("#wrapper_bg > section > section.content_left > div:nth-child(1) > div.anime_video_body > div.anime_muti_link > ul > li");
		
		System.out.print("Data ID : ");
		System.out.println(AnimeUID);
		
		//-- Episdoe Id 
		String EpisodeId = UUID.randomUUID().toString().substring(0 , 7);
		System.out.print("Episode ID : ");
		System.out.println(EpisodeId);
		
		
		
		
		
		
		Insert("INSERT INTO `episodes` (`ID`, `Episode_Titel`, `Episode_Descreption`, `Series_Name`, `Sereis_ID`, `Episode_Number`, `Episode_View`, `Likes_Count`, `Episode_Rate`, `Episode_ReleaseDate`, `Episode_ID`, `Saison_Number`, `Saison_Name`)"
				+ " VALUES "
				+ "(NULL, '"+"Watch Episode "+i+ " Anime " + Name +" English Free"+"', '"+"Watch and Download Episode "+i+ " Anime " + Name +" English Free Sub and Dub "+"', '"+Name+"', '"+AnimeUID+"', '"+i+"', '"+0+"', '"+0+"', '"+"0.0"+"', '"+"04/04/2020"+"', '"+EpisodeId+"', '1', '1');");
		
		
		
		//-- Downlaod
		String DownloadUrl = MainElemetnss.select("#wrapper_bg > section > section.content_left > div:nth-child(1) > div.anime_video_body > div.anime_video_body_cate > a:nth-child(7)").attr("href");
		System.out.print("Download Url : ");
		System.out.println(DownloadUrl);
		Insert("INSERT INTO `download` (`ID`, `Server_Name`, `Download_Type`, `Download_File_Size`, `Download_Quility`, `Data_ID`, `Download_Url`) VALUES (NULL, '"+"VidStream"+"', '"+"Url"+"', '"+"null"+"', '"+"HD"+"', '"+EpisodeId+"', '"+DownloadUrl+"');");
		
		
		
		for(Element df : ServerElements) {
			
			
			
			
			System.out.println("***** Server ******");
			
			// Data ID
			System.out.print("Episode ID : ");
			System.out.println(EpisodeId);
			
			// Server Name
			String ServerName = df.select("li").attr("class");
			System.out.println(ServerName);
			
			// Server Watch Url
			String ServerUrl = df.select("a").attr("data-video");
			System.out.println(ServerUrl);
			
			
			Insert("INSERT INTO `videos` (`ID`, `Video_Link`, `Video_type`, `Data_ID`, `Video_gender`, `Server_Name`) VALUES (NULL, '"+ServerUrl+"', '"+"Watching"+"', '"+EpisodeId+"', '"+"Embaded"+"', '"+ServerName+"');");
			
		}
		
	}catch(Exception e){
		System.out.println(e.toString());
	}
            		
            	
	}
	
	public static void Insert(String query) {
		Connection conn = null;
	      Statement stmt = null;
	      try {
	         try {
	            Class.forName("com.mysql.jdbc.Driver");
	         } catch (Exception dd) {
	            System.out.println(dd);
	      }
	      conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/gogoanime?useUnicode=true&characterEncoding=utf-8", "root", "");
	      stmt = (Statement) conn.createStatement();
	      stmt.executeUpdate(query);
		      } catch (SQLException excep) {
	         excep.printStackTrace();
	      } catch (Exception excep) {
	         excep.printStackTrace();
	      } finally {
	         try {
	            if (stmt != null)
	               conn.close();
	         } catch (SQLException se) {}
	         try {
	            if (conn != null)
	               conn.close();
	         } catch (SQLException se) {
	            se.printStackTrace();
	         }  
	      }
	}
	
		
	}
	
	
	


