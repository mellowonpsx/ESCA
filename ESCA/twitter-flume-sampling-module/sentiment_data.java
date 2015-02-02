// ORM class for table 'sentiment_data'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Thu Jan 08 05:27:06 PST 2015
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

public class sentiment_data extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private Long id;
  public Long get_id() {
    return id;
  }
  public void set_id(Long id) {
    this.id = id;
  }
  public sentiment_data with_id(Long id) {
    this.id = id;
    return this;
  }
  private String created_at;
  public String get_created_at() {
    return created_at;
  }
  public void set_created_at(String created_at) {
    this.created_at = created_at;
  }
  public sentiment_data with_created_at(String created_at) {
    this.created_at = created_at;
    return this;
  }
  private String text;
  public String get_text() {
    return text;
  }
  public void set_text(String text) {
    this.text = text;
  }
  public sentiment_data with_text(String text) {
    this.text = text;
    return this;
  }
  private String type;
  public String get_type() {
    return type;
  }
  public void set_type(String type) {
    this.type = type;
  }
  public sentiment_data with_type(String type) {
    this.type = type;
    return this;
  }
  private Long user_id;
  public Long get_user_id() {
    return user_id;
  }
  public void set_user_id(Long user_id) {
    this.user_id = user_id;
  }
  public sentiment_data with_user_id(Long user_id) {
    this.user_id = user_id;
    return this;
  }
  private String user_username;
  public String get_user_username() {
    return user_username;
  }
  public void set_user_username(String user_username) {
    this.user_username = user_username;
  }
  public sentiment_data with_user_username(String user_username) {
    this.user_username = user_username;
    return this;
  }
  private String user_name;
  public String get_user_name() {
    return user_name;
  }
  public void set_user_name(String user_name) {
    this.user_name = user_name;
  }
  public sentiment_data with_user_name(String user_name) {
    this.user_name = user_name;
    return this;
  }
  private String user_lang;
  public String get_user_lang() {
    return user_lang;
  }
  public void set_user_lang(String user_lang) {
    this.user_lang = user_lang;
  }
  public sentiment_data with_user_lang(String user_lang) {
    this.user_lang = user_lang;
    return this;
  }
  private Long user_influence;
  public Long get_user_influence() {
    return user_influence;
  }
  public void set_user_influence(Long user_influence) {
    this.user_influence = user_influence;
  }
  public sentiment_data with_user_influence(Long user_influence) {
    this.user_influence = user_influence;
    return this;
  }
  private String user_time_zone;
  public String get_user_time_zone() {
    return user_time_zone;
  }
  public void set_user_time_zone(String user_time_zone) {
    this.user_time_zone = user_time_zone;
  }
  public sentiment_data with_user_time_zone(String user_time_zone) {
    this.user_time_zone = user_time_zone;
    return this;
  }
  private Long polarity;
  public Long get_polarity() {
    return polarity;
  }
  public void set_polarity(Long polarity) {
    this.polarity = polarity;
  }
  public sentiment_data with_polarity(Long polarity) {
    this.polarity = polarity;
    return this;
  }
  private String sentiment;
  public String get_sentiment() {
    return sentiment;
  }
  public void set_sentiment(String sentiment) {
    this.sentiment = sentiment;
  }
  public sentiment_data with_sentiment(String sentiment) {
    this.sentiment = sentiment;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof sentiment_data)) {
      return false;
    }
    sentiment_data that = (sentiment_data) o;
    boolean equal = true;
    equal = equal && (this.id == null ? that.id == null : this.id.equals(that.id));
    equal = equal && (this.created_at == null ? that.created_at == null : this.created_at.equals(that.created_at));
    equal = equal && (this.text == null ? that.text == null : this.text.equals(that.text));
    equal = equal && (this.type == null ? that.type == null : this.type.equals(that.type));
    equal = equal && (this.user_id == null ? that.user_id == null : this.user_id.equals(that.user_id));
    equal = equal && (this.user_username == null ? that.user_username == null : this.user_username.equals(that.user_username));
    equal = equal && (this.user_name == null ? that.user_name == null : this.user_name.equals(that.user_name));
    equal = equal && (this.user_lang == null ? that.user_lang == null : this.user_lang.equals(that.user_lang));
    equal = equal && (this.user_influence == null ? that.user_influence == null : this.user_influence.equals(that.user_influence));
    equal = equal && (this.user_time_zone == null ? that.user_time_zone == null : this.user_time_zone.equals(that.user_time_zone));
    equal = equal && (this.polarity == null ? that.polarity == null : this.polarity.equals(that.polarity));
    equal = equal && (this.sentiment == null ? that.sentiment == null : this.sentiment.equals(that.sentiment));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.id = JdbcWritableBridge.readLong(1, __dbResults);
    this.created_at = JdbcWritableBridge.readString(2, __dbResults);
    this.text = JdbcWritableBridge.readString(3, __dbResults);
    this.type = JdbcWritableBridge.readString(4, __dbResults);
    this.user_id = JdbcWritableBridge.readLong(5, __dbResults);
    this.user_username = JdbcWritableBridge.readString(6, __dbResults);
    this.user_name = JdbcWritableBridge.readString(7, __dbResults);
    this.user_lang = JdbcWritableBridge.readString(8, __dbResults);
    this.user_influence = JdbcWritableBridge.readLong(9, __dbResults);
    this.user_time_zone = JdbcWritableBridge.readString(10, __dbResults);
    this.polarity = JdbcWritableBridge.readLong(11, __dbResults);
    this.sentiment = JdbcWritableBridge.readString(12, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(id, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(created_at, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(text, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(type, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(user_id, 5 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(user_username, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(user_name, 7 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(user_lang, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(user_influence, 9 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(user_time_zone, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(polarity, 11 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(sentiment, 12 + __off, 12, __dbStmt);
    return 12;
  }
  public void readFields(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.id = null;
    } else {
    this.id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.created_at = null;
    } else {
    this.created_at = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.text = null;
    } else {
    this.text = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.type = null;
    } else {
    this.type = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.user_id = null;
    } else {
    this.user_id = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.user_username = null;
    } else {
    this.user_username = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.user_name = null;
    } else {
    this.user_name = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.user_lang = null;
    } else {
    this.user_lang = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.user_influence = null;
    } else {
    this.user_influence = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.user_time_zone = null;
    } else {
    this.user_time_zone = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.polarity = null;
    } else {
    this.polarity = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.sentiment = null;
    } else {
    this.sentiment = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.id);
    }
    if (null == this.created_at) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, created_at);
    }
    if (null == this.text) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, text);
    }
    if (null == this.type) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, type);
    }
    if (null == this.user_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.user_id);
    }
    if (null == this.user_username) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, user_username);
    }
    if (null == this.user_name) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, user_name);
    }
    if (null == this.user_lang) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, user_lang);
    }
    if (null == this.user_influence) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.user_influence);
    }
    if (null == this.user_time_zone) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, user_time_zone);
    }
    if (null == this.polarity) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.polarity);
    }
    if (null == this.sentiment) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sentiment);
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
    __sb.append(FieldFormatter.escapeAndEnclose(id==null?"null":"" + id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(created_at==null?"null":created_at, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(text==null?"null":text, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(type==null?"null":type, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_id==null?"null":"" + user_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_username==null?"null":user_username, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_name==null?"null":user_name, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_lang==null?"null":user_lang, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_influence==null?"null":"" + user_influence, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(user_time_zone==null?"null":user_time_zone, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(polarity==null?"null":"" + polarity, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sentiment==null?"null":sentiment, delimiters));
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id = null; } else {
      this.id = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.created_at = null; } else {
      this.created_at = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.text = null; } else {
      this.text = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.type = null; } else {
      this.type = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.user_id = null; } else {
      this.user_id = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.user_username = null; } else {
      this.user_username = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.user_name = null; } else {
      this.user_name = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.user_lang = null; } else {
      this.user_lang = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.user_influence = null; } else {
      this.user_influence = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.user_time_zone = null; } else {
      this.user_time_zone = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.polarity = null; } else {
      this.polarity = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.sentiment = null; } else {
      this.sentiment = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    sentiment_data o = (sentiment_data) super.clone();
    return o;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("id", this.id);
    __sqoop$field_map.put("created_at", this.created_at);
    __sqoop$field_map.put("text", this.text);
    __sqoop$field_map.put("type", this.type);
    __sqoop$field_map.put("user_id", this.user_id);
    __sqoop$field_map.put("user_username", this.user_username);
    __sqoop$field_map.put("user_name", this.user_name);
    __sqoop$field_map.put("user_lang", this.user_lang);
    __sqoop$field_map.put("user_influence", this.user_influence);
    __sqoop$field_map.put("user_time_zone", this.user_time_zone);
    __sqoop$field_map.put("polarity", this.polarity);
    __sqoop$field_map.put("sentiment", this.sentiment);
    return __sqoop$field_map;
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("id".equals(__fieldName)) {
      this.id = (Long) __fieldVal;
    }
    else    if ("created_at".equals(__fieldName)) {
      this.created_at = (String) __fieldVal;
    }
    else    if ("text".equals(__fieldName)) {
      this.text = (String) __fieldVal;
    }
    else    if ("type".equals(__fieldName)) {
      this.type = (String) __fieldVal;
    }
    else    if ("user_id".equals(__fieldName)) {
      this.user_id = (Long) __fieldVal;
    }
    else    if ("user_username".equals(__fieldName)) {
      this.user_username = (String) __fieldVal;
    }
    else    if ("user_name".equals(__fieldName)) {
      this.user_name = (String) __fieldVal;
    }
    else    if ("user_lang".equals(__fieldName)) {
      this.user_lang = (String) __fieldVal;
    }
    else    if ("user_influence".equals(__fieldName)) {
      this.user_influence = (Long) __fieldVal;
    }
    else    if ("user_time_zone".equals(__fieldName)) {
      this.user_time_zone = (String) __fieldVal;
    }
    else    if ("polarity".equals(__fieldName)) {
      this.polarity = (Long) __fieldVal;
    }
    else    if ("sentiment".equals(__fieldName)) {
      this.sentiment = (String) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
}
