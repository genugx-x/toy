import {Component} from 'react';

class ForEach extends Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    componentDidMount() {
        var arr = [3, 2, 1, 7, ,4, 5];
        var newArr = [];

        arr.forEach((i) => {
            newArr.push(i);
        })
        console.log("newArr : " + newArr);
    }


    render() {
        return (
            <h2>[THIS IS ForEach]</h2>
        )
    }
}

export default ForEach;