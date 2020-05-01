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

public class MainPage {

	
	public static void main(final String [] args) {
		System.setProperty("file.encoding", "UTF-8");

		// Stating Threading
		for (int p = 23 ; p<=55 ; p++) {
			System.setProperty("file.encoding", "UTF-8");

	
			System.out.println("♥♥♥♥♥ ReDev! ♥♥♥♥♥");
			final String BaseUrl = "https://www18.gogoanime.io/";
				System.out.println("Page => " + p );
	
				final String Url = "https://www18.gogoanime.io/anime-list.html?page="+p;
	
			try {
				System.out.println("Working on it ...");
	
				final Document doc = Jsoup.connect(Url).get();
				final Elements mainELements = doc.select("#wrapper_bg > section > section.content_left > div > div.anime_list_body > ul li");
				int n  = 0 ;
				
				for(final Element e : mainELements) {
					System.out.println("=> " + n++);
					System.out.println("--------------------------------------------- Anime  ------------------------------------------------------");
					final String Link = e.select("a[href]").attr("href");
					System.out.println(Link);
					
					try {
						final Document d = Jsoup.connect("https://www18.gogoanime.io/" +Link ).get();
						final Elements PageElemtns = d.select("body");
						
						//-- Anime Id 
						final String AnimeUID = UUID.randomUUID().toString().substring(0 , 7);
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
						final String Image = PageElemtns.select("#wrapper_bg > section > section.content_left > div.main_body > div:nth-child(2) > div.anime_info_body_bg > img").attr("src");
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
						
						
						final Elements  EpisodeElements = PageElemtns.select("#episode_page > li a");
						final String lastEpisode = EpisodeElements.last().attr("ep_end");
						
						
						
						
						
						
						for(int i = 1  ; i<= Integer.valueOf(lastEpisode) ; i++) {
							
							System.out.println("............  Episode  ............");
	
							
							// EpisodeNumber
							System.out.print("Episode Number : ");
							System.out.println(i);
							
							// Episode Url
							final String EpisodeUrl = BaseUrl + Link.replaceAll("category/", "") + "-episode-" +i;
							new MainThread(EpisodeUrl, AnimeUID, i, Name).start();
						
				}
				
			}catch (final Exception gg) {
				System.out.println("Main Error  =>  "   +  gg.toString());
			}
		
	
		}
	}catch(Exception dfd){
		System.out.println(dfd.toString());
	}
}
		}

	public static void Insert(final String query) {
		Connection conn = null;
		Statement stmt = null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (final Exception dd) {
				System.out.println(dd);
			}
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/gogoanime?useUnicode=true&characterEncoding=utf-8", "root", "");
			stmt = (Statement) conn.createStatement();
			stmt.executeUpdate(query);
		} catch (final SQLException excep) {
			excep.printStackTrace();
		} catch (final Exception excep) {
			excep.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (final SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (final SQLException se) {
					se.printStackTrace();
				 }  
			  }
		}
		
}
