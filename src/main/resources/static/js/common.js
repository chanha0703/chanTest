const SmartDoc = (function () {
  
  function post(url, data, successCallback, errorCallback) {
    $.ajax({
      url: url,
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(data),
      success: successCallback,
      error: errorCallback || function (err) {
        console.error(`[POST ERROR] ${url}`, err);
        alert('오류가 발생했습니다.');
      }
    });
  }
  
  const DocumentMaster = {
    save: function (data, onSuccess) {
      post('/api/document-master/save', data, onSuccess);
    },
    load: function (idx, onSuccess) {
      post('/api/document-master/load', { idx: idx }, onSuccess);
    },
    getLatest: function (docNo, onSuccess) {
      post('/api/document-master/latest', { docNo: docNo }, onSuccess);
    },
    getList: function(data, onSuccess) {
      post('/api/document-master/list', data, onSuccess);
    }
  };
  
  const WorkDocument = {
    save: function (data, onSuccess) {
      post('/api/work-document/save', data, onSuccess);
    },
    load: function (docNo, onSuccess) {
      post('/api/work-document/load', { docNo: docNo }, onSuccess);
    },
    rollback: function (docNo, version, onSuccess) {
      post('/api/work-document/rollback', { docNo: docNo, version: version }, onSuccess);
    }
  };
  
  return {
    post: post,
    DocumentMaster: DocumentMaster,
    WorkDocument: WorkDocument
  };
  
})();
