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
    getList: function(data, onSuccess) {
      post('/api/document-master/list', data, onSuccess);
    },
    getHistory: function (idx, onSuccess) {
      post('/api/document-master/history', { idx: idx }, onSuccess);
    }
  };
  
  const WorkDocument = {
    save: function (data, onSuccess) {
      post('/api/work-document/save', data, onSuccess);
    },
    load: function (idx, onSuccess) {
      post('/api/work-document/load', { idx: idx }, onSuccess);
    },
    getList: function(data, onSuccess) {
      post('/api/work-document/list', data, onSuccess);
    },
    getHistory: function (idx, onSuccess) {
      post('/api/work-document/history', { idx: idx }, onSuccess);
    }
  };
  
  return {
    post: post,
    DocumentMaster: DocumentMaster,
    WorkDocument: WorkDocument
  };
  
})();
