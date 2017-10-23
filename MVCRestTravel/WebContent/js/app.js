$(document).ready(function(){
  console.log("loaded");
  $(loggForm.submit).click(loggingHandler);
  $(addDestination.submit).click(handleCreate);
  $("#add-dest").hide();
});

var loggingHandler = function(ev){
  ev.preventDefault();
  var logType = $(this).val();
  console.log(logType);
  if (logType == "Logg in") {
    loggInn();
  } else {
    loggOff();
  }

};

var loggInn = function(){
  var userName = $(loggForm.userName).val();
  var password = $(loggForm.password).val();
  var user = {
        "userName": userName,
        "password": password
    }
  $.ajax({
    url: "rest/login",
    type: "POST",
    contentType: 'application/json',
    dataType: 'json',
    data: JSON.stringify(user)
  }).done(function(data){
    $(loggForm.id).val(data.id);
    $(loggForm.submit).val("Logg off");
    $("#logg-err").text("");
    $("#notLogged").toggle();
    $("#user-name").text("Hello " + data.userName);
    displayDestination();
    $("#add-dest").show();
  }).fail(function(){
    $("#logg-err").text("Your Information incorrect");
  });
};

var loggOff = function(){
  $(loggForm.id).val("");
  $(loggForm.submit).val("Logg in");
  $("#notLogged").toggle();
  $("#user-name").text("");
  $("#dest-table").html("");
  $("#add-dest").hide();
}

var displayDestination = function(){
  var userId = $(loggForm.id).val();
  $.ajax({
    url: "rest/users/"+ userId +"/destinations",
    type: "GET",
    dataType: 'json',
  }).done(function(data){
    $("#dest-table").html("").append(createDestinationTable(data));
  });
}

var createDestinationTable = function(data){
  var totalCost = 0;
  var table = $('<table>');
  var headName = $('<th>').text('Destination');
  var headCost = $('<th>').text('Cost');
  var headView = $('<th>').text('View');
  var headDelet = $('<th>').text();
  var hrow = $('<tr>').append(headName).append(headCost).append(headView).append(headDelet);
  var thead = $('<thead>').append(hrow);
  var tbody = $('<tbody>');
  table.append(thead);
  data.forEach(function(dest){
    var name = $('<td>').text(dest.name);
    var cost = figureCost(dest.activities);
    totalCost += cost;
    var costTd = $('<td>').text(cost);
    var button1 = $('<button>').attr('data-id',dest.id).text("view").click(handleDestView);
    var button2 = $('<button>').attr('data-id',dest.id).text("X").click(handleDestDel);
    var row = $("<tr>");
    row.append(name).append(costTd).append(button1).append(button2);
    tbody.append(row);
  });
  var totalTd = $("<td>").text("Total Cost");
  var finalCostTd = $("<td>").text(totalCost);
  var endRow = $("<tr>").append(totalTd).append(finalCostTd);
  tbody.append(endRow);
  table.append(tbody);
  return table;
}

var figureCost = function(activities){
  var total= 0;
  activities.forEach(function(activity){
    total += activity.cost;
  });
  return total;
}
var handleDestView = function(ev){
  var destId = $(this).attr("data-id");
  console.log(destId);
}

var handleDestDel = function(ev) {
  var destId = $(this).attr("data-id");
  var userId = $(loggForm.id).val();
  $.ajax({
    url: "rest/users/"+ userId +"/destinations/"+destId,
    type: "DELETE",
    dataType: 'json',
  }).done(function(data){
    $("#dest-table").html("").append(createDestinationTable(data));
  });
}

var handleCreate = function(ev){
  ev.preventDefault();
  var name = $(addDestination.name).val();
  var image = $(addDestination.imgUrl).val();
  var dest = {
        "name": name,
        "imgUrl": image
  }
  var userId = $(loggForm.id).val();
  $.ajax({
    url: "rest/users/" + userId + "/destinations",
    type: "POST",
    contentType: 'application/json',
    dataType: 'json',
    data: JSON.stringify(dest)
  }).done(function(data){
    $("#dest-table").html("").append(createDestinationTable(data));
  });
  console.log("create");
}
