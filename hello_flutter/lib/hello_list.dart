import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:hello_flutter/main.dart';

class HelloList extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _HelloListState();
  }
}

class _HelloListState extends State<HelloList> {
  var _hellos = AppContext.instance.hellos;

  @override
  Widget build(BuildContext context) {
    return Column(children: <Widget>[
      Expanded(child: ListView(children: _buildRows())),
      Row(mainAxisAlignment: MainAxisAlignment.end, children: <Widget>[
        Container(
            padding: EdgeInsets.all(8.0),
            child: FloatingActionButton(
                onPressed: () => _add(context),
                backgroundColor: Colors.green,
                child: Icon(Icons.add)))
      ])
    ]);
  }

  _buildRows() {
    return _hellos.map<Widget>((hello) => _buildRow(hello)).toList();
  }

  _buildRow(Hello hello) {
    return Column(
      children: <Widget>[
        Dismissible(
            direction: DismissDirection.endToStart,
            key: Key(hello.id.toString()),
            background: Container(
                alignment: AlignmentDirectional.centerEnd,
                padding: EdgeInsets.all(8.0),
                child: Icon(Icons.delete)),
            onDismissed: (direction) {
              setState(() {
                _hellos.remove(hello);
              });
            },
            child: ListTile(
                leading: Text(
                  "#${hello.id}",
                  style: TextStyle(color: Colors.black38),
                  textScaleFactor: 1.2,
                ),
                title: Text(
                  hello.message,
                  style: TextStyle(fontWeight: FontWeight.bold),
                  textScaleFactor: 1.2,
                ))),
        Divider()
      ],
    );
  }

  void _add(BuildContext context) {
    var textCtrl = TextEditingController();
    showDialog(
        context: context,
        builder: (ctx) => AlertDialog(
              title: Text("New message"),
              content: TextField(controller: textCtrl, autofocus: true),
              actions: <Widget>[
                FlatButton(
                    child: Text("OK"),
                    onPressed: () {
                      setState(() {
                        _hellos.add(Hello(_hellos.length + 1, textCtrl.text));
                      });
                      Navigator.pop(context);
                    }),
                FlatButton(
                    child: Text("Cancel"),
                    onPressed: () {
                      Navigator.pop(context);
                    })
              ],
            ));
  }
}
