// ORM class for table 'sentiment_daily_report'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Sat Jan 10 06:16:32 PST 2015
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class sentiment_daily_report extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private java.sql.Timestamp full_date_time;
  public java.sql.Timestamp get_full_date_time() {
    return full_date_time;
  }
  public void set_full_date_time(java.sql.Timestamp full_date_time) {
    this.full_date_time = full_date_time;
  }
  public sentiment_daily_report with_full_date_time(java.sql.Timestamp full_date_time) {
    this.full_date_time = full_date_time;
    return this;
  }
  private Long sentiment_value;
  public Long get_sentiment_value() {
    return sentiment_value;
  }
  public void set_sentiment_value(Long sentiment_value) {
    this.sentiment_value = sentiment_value;
  }
  public sentiment_daily_report with_sentiment_value(Long sentiment_value) {
    this.sentiment_value = sentiment_value;
    return this;
  }
  private Long polarity_value;
  public Long get_polarity_value() {
    return polarity_value;
  }
  public void set_polarity_value(Long polarity_value) {
    this.polarity_value = polarity_value;
  }
  public sentiment_daily_report with_polarity_value(Long polarity_value) {
    this.polarity_value = polarity_value;
    return this;
  }
  private Long positive_sentiment;
  public Long get_positive_sentiment() {
    return positive_sentiment;
  }
  public void set_positive_sentiment(Long positive_sentiment) {
    this.positive_sentiment = positive_sentiment;
  }
  public sentiment_daily_report with_positive_sentiment(Long positive_sentiment) {
    this.positive_sentiment = positive_sentiment;
    return this;
  }
  private Long negative_sentiment;
  public Long get_negative_sentiment() {
    return negative_sentiment;
  }
  public void set_negative_sentiment(Long negative_sentiment) {
    this.negative_sentiment = negative_sentiment;
  }
  public sentiment_daily_report with_negative_sentiment(Long negative_sentiment) {
    this.negative_sentiment = negative_sentiment;
    return this;
  }
  private Long neutral_sentiment;
  public Long get_neutral_sentiment() {
    return neutral_sentiment;
  }
  public void set_neutral_sentiment(Long neutral_sentiment) {
    this.neutral_sentiment = neutral_sentiment;
  }
  public sentiment_daily_report with_neutral_sentiment(Long neutral_sentiment) {
    this.neutral_sentiment = neutral_sentiment;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof sentiment_daily_report)) {
      return false;
    }
    sentiment_daily_report that = (sentiment_daily_report) o;
    boolean equal = true;
    equal = equal && (this.full_date_time == null ? that.full_date_time == null : this.full_date_time.equals(that.full_date_time));
    equal = equal && (this.sentiment_value == null ? that.sentiment_value == null : this.sentiment_value.equals(that.sentiment_value));
    equal = equal && (this.polarity_value == null ? that.polarity_value == null : this.polarity_value.equals(that.polarity_value));
    equal = equal && (this.positive_sentiment == null ? that.positive_sentiment == null : this.positive_sentiment.equals(that.positive_sentiment));
    equal = equal && (this.negative_sentiment == null ? that.negative_sentiment == null : this.negative_sentiment.equals(that.negative_sentiment));
    equal = equal && (this.neutral_sentiment == null ? that.neutral_sentiment == null : this.neutral_sentiment.equals(that.neutral_sentiment));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.full_date_time = JdbcWritableBridge.readTimestamp(1, __dbResults);
    this.sentiment_value = JdbcWritableBridge.readLong(2, __dbResults);
    this.polarity_value = JdbcWritableBridge.readLong(3, __dbResults);
    this.positive_sentiment = JdbcWritableBridge.readLong(4, __dbResults);
    this.negative_sentiment = JdbcWritableBridge.readLong(5, __dbResults);
    this.neutral_sentiment = JdbcWritableBridge.readLong(6, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeTimestamp(full_date_time, 1 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeLong(sentiment_value, 2 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(polarity_value, 3 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(positive_sentiment, 4 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(negative_sentiment, 5 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeLong(neutral_sentiment, 6 + __off, -5, __dbStmt);
    return 6;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.full_date_time = null;
    } else {
    this.full_date_time = new Timestamp(__dataIn.readLong());
    this.full_date_time.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.sentiment_value = null;
    } else {
    this.sentiment_value = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.polarity_value = null;
    } else {
    this.polarity_value = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.positive_sentiment = null;
    } else {
    this.positive_sentiment = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.negative_sentiment = null;
    } else {
    this.negative_sentiment = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.neutral_sentiment = null;
    } else {
    this.neutral_sentiment = Long.valueOf(__dataIn.readLong());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.full_date_time) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.full_date_time.getTime());
    __dataOut.writeInt(this.full_date_time.getNanos());
    }
    if (null == this.sentiment_value) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.sentiment_value);
    }
    if (null == this.polarity_value) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.polarity_value);
    }
    if (null == this.positive_sentiment) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.positive_sentiment);
    }
    if (null == this.negative_sentiment) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.negative_sentiment);
    }
    if (null == this.neutral_sentiment) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.neutral_sentiment);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(full_date_time==null?"null":"" + full_date_time, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sentiment_value==null?"null":"" + sentiment_value, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(polarity_value==null?"null":"" + polarity_value, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(positive_sentiment==null?"null":"" + positive_sentiment, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(negative_sentiment==null?"null":"" + negative_sentiment, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(neutral_sentiment==null?"null":"" + neutral_sentiment, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.full_date_time = null; } else {
      this.full_date_time = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.sentiment_value = null; } else {
      this.sentiment_value = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.polarity_value = null; } else {
      this.polarity_value = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.positive_sentiment = null; } else {
      this.positive_sentiment = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.negative_sentiment = null; } else {
      this.negative_sentiment = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.neutral_sentiment = null; } else {
      this.neutral_sentiment = Long.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    sentiment_daily_report o = (sentiment_daily_report) super.clone();
    o.full_date_time = (o.full_date_time != null) ? (java.sql.Timestamp) o.full_date_time.clone() : null;
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("full_date_time", this.full_date_time);
    __sqoop$field_map.put("sentiment_value", this.sentiment_value);
    __sqoop$field_map.put("polarity_value", this.polarity_value);
    __sqoop$field_map.put("positive_sentiment", this.positive_sentiment);
    __sqoop$field_map.put("negative_sentiment", this.negative_sentiment);
    __sqoop$field_map.put("neutral_sentiment", this.neutral_sentiment);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("full_date_time".equals(__fieldName)) {
      this.full_date_time = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("sentiment_value".equals(__fieldName)) {
      this.sentiment_value = (Long) __fieldVal;
    }
    else    if ("polarity_value".equals(__fieldName)) {
      this.polarity_value = (Long) __fieldVal;
    }
    else    if ("positive_sentiment".equals(__fieldName)) {
      this.positive_sentiment = (Long) __fieldVal;
    }
    else    if ("negative_sentiment".equals(__fieldName)) {
      this.negative_sentiment = (Long) __fieldVal;
    }
    else    if ("neutral_sentiment".equals(__fieldName)) {
      this.neutral_sentiment = (Long) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
