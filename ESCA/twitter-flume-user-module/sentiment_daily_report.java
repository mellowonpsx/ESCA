// ORM class for table 'sentiment_daily_report'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Thu Jan 08 04:56:47 PST 2015
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
  private java.sql.Date date;
  public java.sql.Date get_date() {
    return date;
  }
  public void set_date(java.sql.Date date) {
    this.date = date;
  }
  public sentiment_daily_report with_date(java.sql.Date date) {
    this.date = date;
    return this;
  }
  private Integer sentiment_value;
  public Integer get_sentiment_value() {
    return sentiment_value;
  }
  public void set_sentiment_value(Integer sentiment_value) {
    this.sentiment_value = sentiment_value;
  }
  public sentiment_daily_report with_sentiment_value(Integer sentiment_value) {
    this.sentiment_value = sentiment_value;
    return this;
  }
  private Integer polarity_value;
  public Integer get_polarity_value() {
    return polarity_value;
  }
  public void set_polarity_value(Integer polarity_value) {
    this.polarity_value = polarity_value;
  }
  public sentiment_daily_report with_polarity_value(Integer polarity_value) {
    this.polarity_value = polarity_value;
    return this;
  }
  private Integer positive_sentiment;
  public Integer get_positive_sentiment() {
    return positive_sentiment;
  }
  public void set_positive_sentiment(Integer positive_sentiment) {
    this.positive_sentiment = positive_sentiment;
  }
  public sentiment_daily_report with_positive_sentiment(Integer positive_sentiment) {
    this.positive_sentiment = positive_sentiment;
    return this;
  }
  private Integer negative_sentiment;
  public Integer get_negative_sentiment() {
    return negative_sentiment;
  }
  public void set_negative_sentiment(Integer negative_sentiment) {
    this.negative_sentiment = negative_sentiment;
  }
  public sentiment_daily_report with_negative_sentiment(Integer negative_sentiment) {
    this.negative_sentiment = negative_sentiment;
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
    equal = equal && (this.date == null ? that.date == null : this.date.equals(that.date));
    equal = equal && (this.sentiment_value == null ? that.sentiment_value == null : this.sentiment_value.equals(that.sentiment_value));
    equal = equal && (this.polarity_value == null ? that.polarity_value == null : this.polarity_value.equals(that.polarity_value));
    equal = equal && (this.positive_sentiment == null ? that.positive_sentiment == null : this.positive_sentiment.equals(that.positive_sentiment));
    equal = equal && (this.negative_sentiment == null ? that.negative_sentiment == null : this.negative_sentiment.equals(that.negative_sentiment));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.date = JdbcWritableBridge.readDate(1, __dbResults);
    this.sentiment_value = JdbcWritableBridge.readInteger(2, __dbResults);
    this.polarity_value = JdbcWritableBridge.readInteger(3, __dbResults);
    this.positive_sentiment = JdbcWritableBridge.readInteger(4, __dbResults);
    this.negative_sentiment = JdbcWritableBridge.readInteger(5, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeDate(date, 1 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeInteger(sentiment_value, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(polarity_value, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(positive_sentiment, 4 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(negative_sentiment, 5 + __off, 4, __dbStmt);
    return 5;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.date = null;
    } else {
    this.date = new Date(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.sentiment_value = null;
    } else {
    this.sentiment_value = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.polarity_value = null;
    } else {
    this.polarity_value = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.positive_sentiment = null;
    } else {
    this.positive_sentiment = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.negative_sentiment = null;
    } else {
    this.negative_sentiment = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.date) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.date.getTime());
    }
    if (null == this.sentiment_value) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.sentiment_value);
    }
    if (null == this.polarity_value) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.polarity_value);
    }
    if (null == this.positive_sentiment) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.positive_sentiment);
    }
    if (null == this.negative_sentiment) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.negative_sentiment);
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
    __sb.append(FieldFormatter.escapeAndEnclose(date==null?"null":"" + date, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sentiment_value==null?"null":"" + sentiment_value, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(polarity_value==null?"null":"" + polarity_value, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(positive_sentiment==null?"null":"" + positive_sentiment, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(negative_sentiment==null?"null":"" + negative_sentiment, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.date = null; } else {
      this.date = java.sql.Date.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.sentiment_value = null; } else {
      this.sentiment_value = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.polarity_value = null; } else {
      this.polarity_value = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.positive_sentiment = null; } else {
      this.positive_sentiment = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.negative_sentiment = null; } else {
      this.negative_sentiment = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    sentiment_daily_report o = (sentiment_daily_report) super.clone();
    o.date = (o.date != null) ? (java.sql.Date) o.date.clone() : null;
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("date", this.date);
    __sqoop$field_map.put("sentiment_value", this.sentiment_value);
    __sqoop$field_map.put("polarity_value", this.polarity_value);
    __sqoop$field_map.put("positive_sentiment", this.positive_sentiment);
    __sqoop$field_map.put("negative_sentiment", this.negative_sentiment);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("date".equals(__fieldName)) {
      this.date = (java.sql.Date) __fieldVal;
    }
    else    if ("sentiment_value".equals(__fieldName)) {
      this.sentiment_value = (Integer) __fieldVal;
    }
    else    if ("polarity_value".equals(__fieldName)) {
      this.polarity_value = (Integer) __fieldVal;
    }
    else    if ("positive_sentiment".equals(__fieldName)) {
      this.positive_sentiment = (Integer) __fieldVal;
    }
    else    if ("negative_sentiment".equals(__fieldName)) {
      this.negative_sentiment = (Integer) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
