import { Component } from "react";

class R007_LifecycleEx extends Component {

    // 2
    static getDerivedStateFromProps(props, state) {
        console.log('2. getDerivedStateFromProps call : ' + props.prop_value);
        return {tmp_state:props.prop_value};
    }

    // 1
    constructor(props) {
        super(props);
        this.state = {};
        console.log('1. constructor call');
    }

    // 3
    render() {
        console.log('3. render call');
        return (
            <h2>[THIS IS CONSTRUCTOR FUNCTION]</h2>
        )
    }

    // 4
    componentDidMount() {
        console.log('4. componentDidMount call');
        console.log('5. tmp_state : ' + this.state.tmp_state);
    }
}

export default R007_LifecycleEx;