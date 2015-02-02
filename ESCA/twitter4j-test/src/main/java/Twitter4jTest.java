package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import twitter4j.Paging;
import twitter4j.RateLimitStatus;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.User;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

public class Twitter4jTest
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String consumerKey;
		String consumerSecret;
		String accessToken;
		String accessTokenSecret;
		
		/** set data */
		consumerKey = "rudqnApw1B6fihcAP21GsDHye";
		consumerSecret = "p0lcTNuISysO4fEcMr2vqTyr7yiSCDsL7hO0dtIxAhig855bcx";
		accessToken = "164594239-aAzqcZz1pmADe9QEncyxtJGKtOEJ702a1YX6Hq9r";
		accessTokenSecret = "ezzaUUgalDrVpWYO89wIKFxaL7f5wxfWvSFC5Xxuif95O";
		/** The actual Twitter stream. It's set up to collect raw JSON data */
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(consumerKey);
		cb.setOAuthConsumerSecret(consumerSecret);
		cb.setOAuthAccessToken(accessToken);
		cb.setOAuthAccessTokenSecret(accessTokenSecret);
		cb.setJSONStoreEnabled(true);
		cb.setIncludeEntitiesEnabled(true);

		//configuration
		String screenName;
		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		try
		{
			//verify user is logged
			User user = twitter.verifyCredentials();
			screenName = user.getScreenName();
	    } catch (TwitterException e)
	    {
	    	//wrong credentials
	        //-> log!!
	    	System.out.println("Failed to verify credentials: " + e.getMessage());
	    	System.exit(-1);
	    } catch (IllegalStateException e)
	    {
	    	//credentials missing
	    	 //-> log!!
	    	System.out.println("Failed to verify credentials: " + e.getMessage());
	    	System.exit(-1);
	    }/*
		try //get "all" old tweet
		{
			int pageNumber = 1;
			List<Status> statuses = new ArrayList<Status>();
			while(true)
			{
				Map<String ,RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus();
				RateLimitStatus mentionsLimit = rateLimitStatus.get("/statuses/mentions_timeline");
				int remaning = mentionsLimit.getRemaining();
				// ->log
				System.out.println("number of remaning mention: "+remaning);
				if(remaning > 0)
				{
					//get the data!
					try
			    	{
			    		int oldSize = statuses.size();
			    		Paging page = new Paging(pageNumber++, 200);
			    		//test need more data
			    		statuses.addAll(twitter.getMentionsTimeline(page));
			    		//statuses.addAll(twitter.getHomeTimeline(page));
			    	    if (statuses.size() == oldSize)
			    	    {
			    	    	// no data added!
			    	    	System.out.println("Numero di DM: "+oldSize);
			    	    	break;
			    	    }
					} catch (TwitterException e)
					{
						//TODO Auto-generated catch block
						System.out.println("Mention Limit reached");
						e.printStackTrace();
						break; //in case of exception, get data
					}
				}
				else
				{
					// wait until reset time (up to 15 minutes!)
					//-> log how much time you have to wait!
					System.out.println("devo aspettare: "+mentionsLimit.getSecondsUntilReset());
					Thread.sleep(mentionsLimit.getSecondsUntilReset()*1000);
					//rateLimitStatus.clear();
					rateLimitStatus = null;
				}
			}
			System.out.println(statuses);
		} catch (Exception e)
		{
			// log exception!!
			e.printStackTrace();
		}
		
		try //get "all" direct messages
		{
			int pageNumber = 1;
			List<DirectMessage> messages = new ArrayList<DirectMessage>();
			while(true)
			{
				Map<String ,RateLimitStatus> rateLimitDM = twitter.getRateLimitStatus();
				RateLimitStatus messageLimit = rateLimitDM.get("/direct_messages");
				int remaning = messageLimit.getRemaining();
				System.out.println("number of remaning DM: "+remaning);
				if(remaning > 0)
				{
					//get the data!
					try
			    	{
						int oldSize = messages.size();
			    		Paging page = new Paging(pageNumber++, 200);
			    		messages.addAll(twitter.getDirectMessages(page));
			    	    if (messages.size() == oldSize)
			    	    {
			    	    	System.out.println("Numero di tweet: "+oldSize);
			    	    	break;
			    	    }
					} catch (TwitterException e)
					{
						//TODO Auto-generated catch block
						System.out.println("DM Limit reached");
						e.printStackTrace();
						break; //in case of exception, get data
					}
				}
				else
				{
					// wait until reset time (up to 15 minutes!)
					//-> log how much time you have to wait!
					System.out.println("devo aspettare: "+messageLimit.getSecondsUntilReset());
					Thread.sleep(messageLimit.getSecondsUntilReset()*1000);
					//rateLimitStatus.clear();
					rateLimitDM = null;
				}
			}
			System.out.println(messages);
		} catch (Exception e)
		{
			// log exception!!
			e.printStackTrace();
		}
		/*
		
	    List<DirectMessage> messages = new ArrayList<DirectMessage>();
	    pageNumber = 1;
	    while(true)
	    {
	    	try
	    	{
	    		int oldSize = messages.size();
	    		Paging page = new Paging(pageNumber++, 200);
	    		messages.addAll(twitter.getDirectMessages(page));
	    	    if (messages.size() == oldSize)
	    	    {
	    	    	// no data added!
	    	    	break;
	    	    }
	    	    System.out.println("Actual size: "+oldSize);
			} catch (TwitterException e)
			{
				//TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
	    }
	    
	    System.out.println("Showing home timeline.");
		int i = 1;
    	for (Status status : statuses)
    	{
        	System.out.println(i + "-" + status.getUser().getName() + ":" + status.getHashtagEntities() + 
                           status.getText());
        	i++;
    	}
    	
    	System.out.println("Showing messages.");
    	i=1;
    	for (DirectMessage message : messages)
    	{
        	System.out.println(i + "-" + message.getSender().getName() + ":" +
		        		message.getText());
        	i++;
    	}
    	
/*    	try
		{
			Map<String ,RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus(""/statuses/mentions_timeline");
	        for (String endpoint : rateLimitStatus.keySet())
	        {
	            RateLimitStatus status = rateLimitStatus.get(endpoint);
	            System.out.println("Endpoint: " + endpoint);
	            System.out.println("Limit: " + status.getLimit());
	            System.out.println("Remaining: " + status.getRemaining());
	            System.out.println("ResetTimeInSeconds: " + status.getResetTimeInSeconds());
	            System.out.println("SecondsUntilReset: " + status.getSecondsUntilReset());
	        }
			//System.out.println(twitter.getAPIConfiguration());
			//System.out.println(twitter.getAccountSettings());
		} catch (TwitterException e)
		{
			e.printStackTrace();
			return;
		}
*/
	}
}
