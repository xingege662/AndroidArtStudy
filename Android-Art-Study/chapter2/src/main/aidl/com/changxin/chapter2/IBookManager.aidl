// IBookManager.aidl
package com.changxin.chapter2;

// Declare any non-default types here with import statements
import com.changxin.chapter2.Book;
import com.changxin.chapter2.IOnNewBookArrivedListener;
interface IBookManager {
   List<Book> getBook();
   //所有的非基本参数都需要一个定向tag来指出数据流通的方式，不管是 in , out , 还是 inout
   //AIDL中的定向 tag 表示了在跨进程通信中数据的流向，其中 in 表示数据只能由客户端流向服务端， out 表示数据只能由服务端流向客户端，而 inout 则表示数据可在服务端与客户端之间双向流通
   void addBook(in Book book);//非基本类型的传输在传输中必须有tag，不然会报错，基本类型的都是in
   void registerListener(IOnNewBookArrivedListener listener);
   void unregisterListener(IOnNewBookArrivedListener listener);
}
