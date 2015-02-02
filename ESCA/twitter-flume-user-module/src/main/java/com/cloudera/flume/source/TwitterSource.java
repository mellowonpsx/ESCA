/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cloudera.flume.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.fs.FileSystem;
import org.apache.commons.httpclient.URI;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDrivenSource;
import org.apache.flume.channel.ChannelProcessor;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.source.AbstractSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

/**
 * A Flume Source, which pulls data from Twitter's streaming API. Currently,
 * this only supports pulling from the sample API, and only gets new status
 * updates.
 */
public class TwitterSource extends AbstractSource
    implements EventDrivenSource, Configurable {

  private static final Logger logger =
      LoggerFactory.getLogger(TwitterSource.class);

  /** Information necessary for accessing the Twitter API */
  private String consumerKey;
  private String consumerSecret;
  private String accessToken;
  private String accessTokenSecret;
  private String screenName;
  private Boolean isTesting;
  private Boolean stop;

  /** The actual Twitter stream. It's set up to collect raw JSON data */
  private TwitterStream twitterStream;

  /**
   * The initialization method for the Source. The context contains all the
   * Flume configuration info, and can be used to retrieve any configuration
   * values necessary to set up the Source.
   */
  @Override
  public void configure(Context context) {
    consumerKey = context.getString(TwitterSourceConstants.CONSUMER_KEY_KEY);
    consumerSecret = context.getString(TwitterSourceConstants.CONSUMER_SECRET_KEY);
    accessToken = context.getString(TwitterSourceConstants.ACCESS_TOKEN_KEY);
    accessTokenSecret = context.getString(TwitterSourceConstants.ACCESS_TOKEN_SECRET_KEY);
    
    screenName = context.getString(TwitterSourceConstants.SCREEN_NAME);
    isTesting = context.getBoolean(TwitterSourceConstants.IS_TESTING);

    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setOAuthConsumerKey(consumerKey);
    cb.setOAuthConsumerSecret(consumerSecret);
    cb.setOAuthAccessToken(accessToken);
    cb.setOAuthAccessTokenSecret(accessTokenSecret);
    cb.setJSONStoreEnabled(true);
    cb.setIncludeEntitiesEnabled(true);
    Configuration configurationBuild = cb.build();
    
	try
	{
	    twitterStream = new TwitterStreamFactory(configurationBuild).getInstance();
    } catch (Exception e)
    {
    	//wrong credentials //credentials missing
    	logger.error("Failed to verify credentials (wrong credentials?): " + e.getMessage());
    }
	
	
  }

  /**
   * Start processing events. This uses the Twitter Streaming API to sample
   * Twitter, and process tweets.
   */
  @Override
  public void start()
  {
    // The channel is the piece of Flume that sits between the Source and Sink,
    // and is used to process events.
    final ChannelProcessor channel = getChannelProcessor();
    final Map<String, String> headers = new HashMap<String, String>();
    
    /** start listner! */
    
    // The StatusListener is a twitter4j API, which can be added to a Twitter
    // stream, and will execute methods every time a message comes in through
    // the stream.
    UserStreamListener listener = new UserStreamListener()
    {
      // The onStatus method is executed every time a new tweet comes in.
      public void onStatus(Status status)
      {
        // The EventBuilder is used to build an event using the headers and
        // the raw JSON of a tweet
        //logger.debug(status.getUser().getScreenName() + ": " + status.getText());
    	  
    	  
    	  //controllare se si è dentro la lista dei menzionati!
    	  // screenname è salvato in fase di configurazione
    	  //logger.info(" test 1: "+ status.getText().contains("@"+screenName)+" test2: "+ isTesting +" test sum: " +(status.getText().contains("@"+screenName) || isTesting));
    	  if(status.getText().contains("@"+screenName) || isTesting) //if is testing do not filter non-mention messages
    	  {
    		  headers.put("timestamp", String.valueOf(status.getCreatedAt().getTime()));
    		  Event event = EventBuilder.withBody(DataObjectFactory.getRawJSON(status).getBytes(), headers);
    		  channel.processEvent(event);
    	  }
      }
      
      public void onDirectMessage(DirectMessage directMessage)
      {
    	  /*headers.put("timestamp", String.valueOf(directMessage.getCreatedAt().getTime()));
          Event event = EventBuilder.withBody(DataObjectFactory.getRawJSON(directMessage).getBytes(), headers);
          channel.processEvent(event);*/
      }

      // This listener will ignore everything except for new tweets
      public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice){}
      public void onDeletionNotice(long directMessageId, long userId){}
      public void onTrackLimitationNotice(int numberOfLimitedStatuses){}
      public void onScrubGeo(long userId, long upToStatusId){}
      public void onStallWarning(StallWarning warning){}
      public void onFriendList(long[] friendIds){}
      public void onFavorite(User source, User target, Status favoritedStatus){}
      public void onUnfavorite(User source, User target, Status unfavoritedStatus){}
      public void onFollow(User source, User followedUser){}
      public void onUnfollow(User source, User followedUser){}
      public void onUserListMemberAddition(User addedMember, User listOwner, UserList list){}
      public void onUserListMemberDeletion(User deletedMember, User listOwner, UserList list){}
      public void onUserListSubscription(User subscriber, User listOwner, UserList list){}
      public void onUserListUnsubscription(User subscriber, User listOwner, UserList list){}
      public void onUserListCreation(User listOwner, UserList list){}
      public void onUserListUpdate(User listOwner, UserList list){}
      public void onUserListDeletion(User listOwner, UserList list){}
      public void onUserProfileUpdate(User updatedUser){}
      public void onUserDeletion(long deletedUser){}
      public void onUserSuspension(long suspendedUser){}
      public void onBlock(User source, User blockedUser){}
      public void onUnblock(User source, User unblockedUser){}
      public void onException(Exception e)
      {
    	  logger.error("onException:" + e.getMessage());
      }
    };

    // Set up the stream's listener (defined above),
    twitterStream.addListener(listener);
    //to call .user() a UserStreamListner must be set!
    twitterStream.user();
    super.start();
  }

  /**
   * Stops the Source's event processing and shuts down the Twitter stream.
   */
  @Override
  public void stop() {
    logger.debug("Shutting down...");
    super.stop();
    twitterStream.shutdown();
  }
}
