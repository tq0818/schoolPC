

importScripts('/plugins/ccUpload/upload_files/spark-md5.js');

self.spark=new SparkMD5.ArrayBuffer();
//self.spark=new SparkMD5();

self.addEventListener('message', function (event) {

    var message, block, output;

    message=event.data.message;

    block = event.data.block;
    event = null;
    output = {
        'block' : block
    };

    if (block.end === block.file_size) {

        //self.spark.appendBinary(message);
        self.spark.append(message);
        output.result=self.spark.end();
        self.spark.destroy();
    } else {
        //self.spark.appendBinary(message);
        self.spark.append(message);
    }
    message = null;
    
    self.postMessage(output);

}, false);

