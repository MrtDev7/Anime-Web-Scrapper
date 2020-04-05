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

public class ScrappeOnByOn extends Thread{


/// Send to data base            		
private static java.sql.Connection con = null;
private PreparedStatement pst = null;
private ResultSet rs = null;
private static String url = "jdbc:mysql://localhost:3306/gogoanime";
private static String user = "root";
private static String password = "";
 String Url = "";
 String BaseUrl = "";
public ScrappeOnByOn(String Url , String BaseUrl) {
    this.Url = Url;
    this.BaseUrl = BaseUrl;
}
@Override
public void run() {
    // TODO Auto-generated method stub
    super.run();
    
    try{

    
        Document doc = Jsoup.connect(Url).get();
        Elements mainELements = doc.select("#wrapper_bg > section > section.content_left > div > div.anime_list_body > ul li");
        int n  = 0 ;
        
        for(Element e : mainELements) {
            System.out.println("=> " + n++);
            System.out.println("--------------------------------------------- Anime  ------------------------------------------------------");
            String Link = e.select("a[href]").attr("href");
            System.out.println(Link);
            
            try {
                Document d = Jsoup.connect("https://www17.gogoanime.io/" +Link ).get();
                Elements PageElemtns = d.select("body");
                
                //-- Anime Id 
                String AnimeUID = UUID.randomUUID().toString().substring(0 , 7);
                System.out.print("Anime UID : ");
                System.out.println(AnimeUID);
                
                
                //-- Anime Name
                String Name = PageElemtns.select("#wrapper_bg > section > section.content_left > div.main_body > div:nth-child(2) > div.anime_info_body_bg > h1").text();
                System.out.print("Name : ");
                Name = Name.replaceAll("'", "");
                System.out.println(Name);
                
                
                
                //-- Anime Type
                String Type = PageElemtns.select("#wrapper_bg > section > section.content_left > div.main_body > div:nth-child(2) > div.anime_info_body_bg > p:nth-child(4) > a").text();
                System.out.print("Type : ");
                Type = Type.replaceAll("Status: ", "");
                System.out.println(Type);
                
                
                //-- Anime Story
                String Story = PageElemtns.select("#wrapper_bg > section > section.content_left > div.main_body > div:nth-child(2) > div.anime_info_body_bg > p:nth-child(5)").text();
                System.out.print("Story : ");
                Story = Story.replaceAll("Plot Summary: ", "");
                Story = Story.replaceAll("'", "");
                System.out.println(Story);
                
                
                //-- Anime Genders
                String Genders = PageElemtns.select("#wrapper_bg > section > section.content_left > div.main_body > div:nth-child(2) > div.anime_info_body_bg > p:nth-child(6)").text();
                System.out.print("Gener : ");
                Genders = Genders.replaceAll("Genre: ", "");
                Genders = Genders.replaceAll("'", "");
                System.out.println(Genders);
                
                
                //-- Anime ReleaseDate
                String ReleaseDate = PageElemtns.select("#wrapper_bg > section > section.content_left > div.main_body > div:nth-child(2) > div.anime_info_body_bg > p:nth-child(7)").text();
                System.out.print("Released : ");
                ReleaseDate = ReleaseDate.replaceAll("Released: ", "");
            
                System.out.println(ReleaseDate);
                
                
                //-- Anime Status
                String Status = PageElemtns.select("#wrapper_bg > section > section.content_left > div.main_body > div:nth-child(2) > div.anime_info_body_bg > p:nth-child(8)").text();
                System.out.print("Status : ");
                Status = Status.replaceAll("Status: ", "");
                System.out.println(Status);
                
                
                //-- Anime OtherName
                String OtherName = PageElemtns.select("#wrapper_bg > section > section.content_left > div.main_body > div:nth-child(2) > div.anime_info_body_bg > p:nth-child(9)").text();
                System.out.print("Other name: ");
                OtherName = OtherName.replaceAll("Other name:", "");
                OtherName = OtherName.replaceAll("'", "");
                System.out.println(OtherName);
                
                
                System.out.println("======  Images =======");
                
                //-- Anime Image
                String Image = PageElemtns.select("#wrapper_bg > section > section.content_left > div.main_body > div:nth-child(2) > div.anime_info_body_bg > img").attr("src");
                System.out.print("Image Url : ");
                System.out.println(Image);
                System.out.print("Data ID : ");
                System.out.println(AnimeUID);
                
                // insert image 
                Insert("INSERT INTO `images` (`ID`, `Img_Url`, `Image_Type`, `Data_ID`) VALUES (NULL, '"+Image+"', 'poster', '"+AnimeUID+"');");
                
                
                
                String animetype = "";
                
                if(Type.equals("Movies")) {
                    animetype = "Movies";
                    Insert("INSERT INTO `movies` (`ID`, `Movie_Tital`, `Movie_desc`, `Movie_Name`, `Movie_Story`, `Movie_gender`, `Movie_Date`, `Movie_quilty`,"
                            + " `Movie_section`, `Movie_ID`, `Imdb_Key`, `View_count`, `Likes_Count`, `Imdb_Rate`, `Local_Rate`, `Search_keywords`, `Movie_Time`, `Director`, `OtherName`) VALUES"
                            + " (NULL, '"+"Watch Download Movie "+ Name +" English Free "+"',"
                                         + " '"+"Watch and Download  Movie " + Name +" English Free Sub and Dub"+"',"
                                                + "'"+Name+"', '"+Story+"', '"+Genders+"', '"+ReleaseDate+"', '"+"HD"+"', '"+Type+"', '"+AnimeUID+"', 'N/D', '0', '0', 'N/D', 'N/D', "
                                                + "'"+"Watch Movie" + Name + " , " +" Download Movie " + Name +" , " +"Watch and Download " + Name+"',"
                                                    + " '"+"N/D"+"', 'N/D' , '"+OtherName+"');");
                } else {
                    animetype = "TV Series";
                    Insert("INSERT INTO `series` (`ID`, `Serie_Titel`, `Serie_Desc`, `Serie_Name`, `Serie_Story`, `Serie_genrder`"
                            + ", `Serie_Date`, `Serie_quilty`, `Serie_section`, `Serie_ID`, `Imdb_Key`, `View_count`, `Likes_Count`,"
                            + "`Imdb_Rate`, `Local_Rate`, `Search_keywords`, `Episode_Time`, `Director`, `OtherName`) VALUES"
                            + " (NULL, '"+"Watch Download Anime "+ Name +" English Free"+"',"
                                         + " '"+"Watch and Download  Anime " + Name +" English Free Sub and Dub"+"',"
                                         + "'"+Name+"', '"+Story+"', '"+Genders+"', '"+ReleaseDate+"', '"+"HD"+"', '"+Type+"', '"+AnimeUID+"', 'N/D', '0', '0', 'N/D', 'N/D', "
                                         + "'"+"Watch Movie" + Name + " , " +" Download Movie " + Name +" , " +"Watch and Download " + Name+"',"
                                         + " '"+"N/D"+"','"+"N/D"+"' , '"+OtherName+"');");
                }
                
                // insert posts
                Insert("INSERT INTO `posts` (`ID`, `Post_Title`, `Post_PreviewImg`, `Data_ID`, `Post_Type`, `View_count`, `Likes_Count`) VALUES (NULL, '"+Name+"', '"+Image+"', '"+AnimeUID+"', '"+animetype+"', '0', '0');");
                
                
                Elements  EpisodeElements = PageElemtns.select("#episode_page > li a");
                String lastEpisode = EpisodeElements.last().attr("ep_end");
                
                
                
                
                
                
                for(int i = 1  ; i< Integer.valueOf(lastEpisode) ; i++) {
                    
                    System.out.println("............  Episode  ............");

                    
                    // EpisodeNumber
                    System.out.print("Episode Number : ");
                    System.out.println(i);
                    
                    // Episode Url
                    String EpisodeUrl = BaseUrl + Link.replaceAll("category/", "") + "-episode-" +i;
                    System.out.println(EpisodeUrl);
                    
                    //-- Extract Episode Page Html
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
                    
                }
                
                
                
            }catch(Exception error){
                System.out.println("Anime Page Error  =>  "   +  e.toString());
            }
        }
        
    }catch(Exception e){
        System.out.println("MainThread");
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


