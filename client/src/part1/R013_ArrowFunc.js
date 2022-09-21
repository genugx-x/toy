import {Component} from 'react';

class ArrowFunc extends Component {
    constructor(props) {
        super(props);
        this.state = {
            arrowFunc: 'React200',
            num: 3
        };
    }

    componentDidMount() {
        Function1(1);
        this.Function2(1, 1);
        this.Function3(1, 3);
        this.Function4();
        this.Function5(0, 2, 3);

        function Function1(num) {
            return console.log(num + '. Es5 Function');
        }
    }

    Function2 = (n, m) => {
        let i = n + m;
        console.log(i + '. Arrow Function : ' + this.state.arrowFunc);
    }

    Function3() {
        var this_bind = this;
        setTimeout(function() {
            console.log(this_bind.state.num + '.Es5 Callback Function noBind : ');
            console.log(this.state.arrowFunc);
        }, 100);
    }

    Function4() {
        setTimeout(function() {
            console.log('4. Es5 Callback Function Bind : ' + this.state.arrowFunc);
        }.bind(this), 100);
    }

    Function5 = (i, j, l) => {
        const n = i + j + l;
        setTimeout(() => {
            console.log(n + '. Arrow Callback Function : ' + this.state.arrowFunc);
        }, 100);
    }

    render() {
        return (
            <h2>[THIS IS ArrowFunction]</h2>
        )
    }
}

export default ArrowFunc;