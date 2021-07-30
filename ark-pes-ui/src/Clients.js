import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { Table, Col, Row, Space } from 'antd';

const Clients = () => {
    const [ clients, setClients ] = useState([])

    useEffect(() => {
        axios.get('http://localhost:8081/getAllClients')
          .then((response) => {
            console.log(response);
            setClients(response.data)
          })
    }, [ clients.length ])

    const dataSource = clients.map(client => {
        return {
            ...client,
            key: client.id 
        }
    })

    const columns = [
        {
            title: "Name",
            dataIndex: "name",
            key: "name",
            render: (text, record) => (
                <Space size="middle">
                    <Link to={ {pathname:"/clientDetails", state: record }}>
                        {record.name}
                     </Link>
                </Space>
            )
        },
        {
            title: "Description",
            dataIndex: "description",
            key: "description"
        },
        {
            title: "Type",
            dataIndex: "type",
            key: "type"
        },
        {
            title: "Phone Number",
            dataIndex: "phoneNumber",
            key: "phoneNumber"
        }
    ]

    return(
        <Row justify="center" className='home'>
            <Col span={24}>
                <Table dataSource={dataSource} columns={columns} />;
            </Col>
        </Row>
    )
}

export default Clients;