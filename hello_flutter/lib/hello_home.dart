import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:hello_flutter/main.dart';

class HelloHome extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _HelloHomeState();
  }
}

class _HelloHomeState extends State<HelloHome> {
  final _random = new Random();

  @override
  Widget build(BuildContext context) {
    var currentColor = _randomColor();
    return Container(
        color: currentColor.shade50,
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(_randomMessage(),
                  style: TextStyle(
                      color: currentColor,
                      fontWeight: FontWeight.bold,
                      fontSize: 60.0)),
              Padding(padding: EdgeInsets.all(10.0)),
              FloatingActionButton(
                  backgroundColor: currentColor,
                  foregroundColor: Colors.white,
                  child: Icon(Icons.refresh),
                  onPressed: _refresh)
            ],
          ),
        ));
  }

  _refresh() {
    setState(() {});
  }

  _randomMessage() {
    var hellos = AppContext.instance.hellos;
    var hello = hellos[_random.nextInt(hellos.length)];
    return hello.message;
  }

  _randomColor() {
    var colors = [
      Colors.blue,
      Colors.amber,
      Colors.teal,
      Colors.orange,
      Colors.green,
      Colors.purple,
      Colors.grey,
      Colors.brown
    ];
    return colors[_random.nextInt(colors.length)];
  }
}
